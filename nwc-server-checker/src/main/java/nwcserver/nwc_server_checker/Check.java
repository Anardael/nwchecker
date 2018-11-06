package nwcserver.nwc_server_checker;

import com.nwchecker.server.utils.messages.CheckerMessage;
import com.nwchecker.server.utils.messages.CheckerResponse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

public class Check implements Runnable {
    Socket socket;
    private static String CHECKER = "NWC.exe";
    private static String RUN_FOLDER = "run";
    private static String COMPILERS = "compilers.conf";

    public Check(Socket socket) {
        this.socket = socket;
    }

    void deleteDirectoryStream(Path path) throws IOException {
        Files.walk(path).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
    }

    private void writeToFile(File file, byte[] content) throws FileNotFoundException, IOException {
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(content);
        }
    }

    private static void copyFile(File source, File dest) throws IOException {
        FileChannel sourceChannel = null;
        FileChannel destChannel = null;
        try {
            sourceChannel = new FileInputStream(source).getChannel();
            destChannel = new FileOutputStream(dest).getChannel();
            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
        } finally {
            sourceChannel.close();
            destChannel.close();
        }
    }

    private void writeConfig(File configFile, CheckerMessage message) throws FileNotFoundException {
        try (PrintWriter out = new PrintWriter(configFile)) {
            out.println("RunDir=run");
            out.println("TaskLocation=Tasks");
            out.println("ParticipantAsDir=true");
            out.println("[Problem]");
            out.println("input=" + message.getInputFileName());
            out.println("output=" + message.getOutputFileName());
            out.println("inprefix=Tests" + File.separator + "Problem" + File.separator);
            out.println("outprefix=Tests" + File.separator + "Problem" + File.separator);
            out.println("insuffix=.dat");
            out.println("outsuffix=.sol");
            StringBuilder tests = new StringBuilder();
            StringBuilder score = new StringBuilder();
            StringBuilder time = new StringBuilder();
            for (int i = 0; i < message.getScore().size(); i++) {
                tests.append(i).append(" ");
                score.append(message.getScore().get(i)).append(" ");
                time.append(message.getTime().get(i)).append(" ");
            }
            out.println("tests=" + tests.toString());
            out.println("score=" + score.toString());
            out.println("time=" + score.toString());
            out.println("MASK=task.");
        }

    }

    @Override
    public void run() {
        try {
            ObjectInputStream dataInput = new ObjectInputStream(socket.getInputStream());
            CheckerMessage message = (CheckerMessage) dataInput.readObject();
            File runDir = new File(RUN_FOLDER + File.separator + Thread.currentThread().getId());
            ObjectOutputStream dataOutput = new ObjectOutputStream(socket.getOutputStream());
            CheckerResponse checkerResponse = new CheckerResponse();
            try {
                runDir.mkdirs();
                File taskDir = new File(new File(runDir, "Tasks"), "User");
                taskDir.mkdirs();
                File taskFile = new File(taskDir, "task." + message.getExtension());
                writeToFile(taskFile, message.getUserSolution());
                new File(runDir, "run").mkdir();
                File tests = new File(new File(runDir, "Tests"), "Problem");
                tests.mkdirs();
                for (int i = 0; i < message.getInputData().size(); i++) {
                    writeToFile(new File(tests, "" + i + ".dat"), message.getInputData().get(i));
                    writeToFile(new File(tests, "" + i + ".sol"), message.getOutputData().get(i));
                }
                copyFile(new File(CHECKER), new File(runDir, "NWC.exe"));
                copyFile(new File(COMPILERS), new File(runDir, "compilers.conf"));
                writeConfig(new File(runDir, "config.conf"), message);
                ProcessBuilder process = new ProcessBuilder("NWC.exe", "test");
                process = process.directory(runDir);
                Process exec = process.start();
                exec.waitFor();

                try (BufferedReader br = new BufferedReader(new FileReader(new File (runDir,"save.dat")))) {
                    String line;
                    line = br.readLine();//path
                    line = br.readLine();//result
                    checkerResponse.setResponse(line);
                    line = br.readLine();//score
                    checkerResponse.setScore(Float.parseFloat(line));
                }
                try (BufferedReader br = new BufferedReader(new FileReader(new File (runDir,"NWC.log")))) {
                    String line;
                    StringBuilder log = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        log.append(line).append("\n");
                     }
                    checkerResponse.setLog(log.toString());
                }
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                deleteDirectoryStream(runDir.toPath());
                dataOutput.writeObject(checkerResponse);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
