#include <fstream>
#include <ctype.h>
#include <stdlib.h>
#include "Main.h"
#define PARS_TASKS 1
#define PARS_COMPILERS 2
extern map<string,vector<ParticipantTask> > participants;
extern vector<Task> tasks;
extern string TaskLocation,runDir;
extern vector <CompilerInfo> compilers;
extern bool participantAsDir;
extern bool skipCheckedPrograms;
int parserState=0;
string getCurrentDir();
FileList findFiles(string dir,string filemask);
FileList findConfigs(string dir);
string currentTaskName;
int currentTaskNumber,currentCompilerNumber;
FileList findRecursive(string dir,string filemask);
string currentDir;
string trim(const string& str,const string& drop = " ")
{
 string s=str;   
 string r=s.erase(s.find_last_not_of(drop)+1);
 return r.erase(0,r.find_first_not_of(drop));
}

void addLog(string message)
{
    addLog(message,1);
}
void addLog(string message, int en)
{
    cout<< message;
    if (en) cout<<endl;
    ofstream f((currentDir+"\\NWC.log").c_str(),ios::app);
    f<<message;
    if (en)f<<endl;
    f.close();
}


void addTask(string taskName)
{
    int i=0;
    while (i<taskName.size()&&taskName[i]!='[') i++;
    if (i>=taskName.size()){addLog("error in setting Task"+taskName);return;}
    string res="";i++;
    while (i<taskName.size()&&taskName[i]!=']') {res+=taskName[i];i++;}
    if (i>=taskName.size()){addLog("error in setting Task"+taskName);return;}
    if (parserState==PARS_TASKS)
    {
        currentTaskNumber++;
        currentTaskName=res;
        Task task;
        task.name=currentTaskName;
        tasks.push_back(task);
    }
    if (parserState==PARS_COMPILERS)
    {
        currentCompilerNumber++;
        CompilerInfo compiler;
        compiler.name=res;
        compilers.push_back(compiler);
    }
}
void setValue(string lexem,string value)
{
    for(int i=0;i<lexem.size();i++) lexem[i]=toupper(lexem[i]);

    if (parserState==PARS_TASKS)
    {
        if ("RUNDIR"==lexem)
        {
           runDir=value;
           if (runDir[runDir.length()-1]!='\\')runDir+='\\';
           return;
        }
        if ("TASKLOCATION"==lexem){TaskLocation=value;return;}
        if  ("PARTICIPANTASDIR"==lexem)
        {
                for(int i=0;i<value.size();i++) value[i]=toupper(value[i]);
                if ("TRUE"==value) participantAsDir=1; else
                if ("FALSE"==value)participantAsDir=0; else
                addLog("Participant as Dir error. Not Bool "+value);
                return;
        }
        if  ("SKIPCHECKEDPROGRAMS"==lexem)
        {
                for(int i=0;i<value.size();i++) value[i]=toupper(value[i]);
                if ("TRUE"==value) skipCheckedPrograms=1; else
                if ("FALSE"==value)skipCheckedPrograms=0; else
                addLog("Participant as Dir error. Not Bool "+value);
                return;
        }
        if (currentTaskNumber==-1||currentTaskNumber>=tasks.size())
        {
            addLog("No task defined. can't add lexem "+lexem+" with value "+value);
            return;
        }
        if ("INPUT"==lexem) tasks[currentTaskNumber].inFile=value;
        if ("OUTPUT"==lexem)tasks[currentTaskNumber].outFile=value;
        if ("INPREFIX"==lexem)tasks[currentTaskNumber].inPrefix=value;
        if ("INSUFFIX"==lexem)tasks[currentTaskNumber].inSuffix=value;
        if ("OUTSUFFIX"==lexem)tasks[currentTaskNumber].outSuffix=value;
        if ("OUTPREFIX"==lexem) tasks[currentTaskNumber].outPrefix=value;
        if ("TESTS"==lexem)tasks[currentTaskNumber].testsStr=value;
        if ("SCORE"==lexem)tasks[currentTaskNumber].scoreStr=value;
        if ("MASK"==lexem)tasks[currentTaskNumber].mask=value; 
        if ("TIME"==lexem)tasks[currentTaskNumber].timeStr=value;
        if ("CHECKPROGRAM"==lexem)tasks[currentTaskNumber].checkProgram=value;
        if ("WRONGFORMAT"==lexem)tasks[currentTaskNumber].wrongFormat=atof(value.c_str());
    }
    if (parserState==PARS_COMPILERS)
    {
        if (currentCompilerNumber==-1||currentCompilerNumber>=compilers.size())
        {
            addLog("No compiler defined. can't add lexem "+lexem+" with value "+value);
            return;
        }
        if ("EXTENSION"==lexem)
        {
          for(int i=0;i<value.size();i++) value[i]=toupper(value[i]);                      
          compilers[currentCompilerNumber].extension=value;
        }
        if ("EXEFILE"==lexem) compilers[currentCompilerNumber].exeFile=value;
        if ("INFILE"==lexem) compilers[currentCompilerNumber].inFile=value;
        if ("OUTFILE"==lexem) compilers[currentCompilerNumber].outFile=value;
    }    
}

void decodeLine(string line)
{
    if (line.empty()) return;
    int i=0;
    while (i<line.size()&&isspace(line[i])) i++;
    if (i>=line.size()) return;
    if (line[i]=='#') return;
    if (line[i]=='[') {addTask(line);return;}
    string lexemName="",lexemVal="";
    while (i<line.size()&&line[i]!='='){ lexemName+=line[i];i++;}
    if (i>=line.size()){addLog("error in decode line"+line);return;}   
    i++;
    lexemVal.insert(0,line,i,line.size()-i);
    lexemName=trim(lexemName);
    lexemVal=trim(lexemVal);
    setValue(lexemName,lexemVal);
    
}

int readConfigFile(string FileName)
{
     ifstream f(FileName.c_str());
     if (!f)
     {
            addLog("Config file "+FileName+" not found"); 
            return 1;
     }
     while (!f.eof())
     {
        char buffer [1024];
        f.getline(buffer,1024);
        decodeLine(buffer);
     }
     f.close();
     return 0;
}
void makeTests()
{
    
    for(int i=0;i<tasks.size();i++)
    {
        int testsOffset=0,scoresOffset=0,timesOffset=0;    
        while (testsOffset<tasks[i].testsStr.size()&&scoresOffset<tasks[i].scoreStr.size()
                    &&scoresOffset<tasks[i].scoreStr.size())
        {
            string curTest="";
            while (testsOffset<tasks[i].testsStr.size()&&
                            isspace(tasks[i].testsStr[testsOffset]))testsOffset++;
            while (testsOffset<tasks[i].testsStr.size()&&
                            !isspace(tasks[i].testsStr[testsOffset]))
            {
                curTest+=tasks[i].testsStr[testsOffset];
                testsOffset++;                         
            }
            string curScore="";
            while (scoresOffset<tasks[i].scoreStr.size()&&
                            isspace(tasks[i].scoreStr[scoresOffset]))scoresOffset++;
            while (scoresOffset<tasks[i].scoreStr.size()&&
                            !isspace(tasks[i].scoreStr[scoresOffset]))
            {
                curScore+=tasks[i].scoreStr[scoresOffset];
                scoresOffset++;                         
            }
            string curTime="";
            while (timesOffset<tasks[i].timeStr.size()&&
                            isspace(tasks[i].timeStr[timesOffset]))timesOffset++;
            while (timesOffset<tasks[i].timeStr.size()&&
                            !isspace(tasks[i].timeStr[timesOffset]))
            {
                curTime+=tasks[i].timeStr[timesOffset];
                timesOffset++;                         
            }
            Test readyTest;
            readyTest.maxScore=atof(curScore.c_str());
            readyTest.maxTime=atoi(curTime.c_str());
            readyTest.testFile=tasks[i].inPrefix+curTest+tasks[i].inSuffix;
            readyTest.judgeFile=tasks[i].outPrefix+curTest+tasks[i].outSuffix;
            tasks[i].tests.push_back(readyTest);
        }
    } 
}
int getCompiler(string filename)
{
    for(int i=0;i<filename.length();i++)
       filename[i]=toupper(filename[i]);
    for (int i=0;i<compilers.size();i++)
    {
        string part="."+compilers[i].extension;
        if (filename.find(part)!=string::npos) return i;
    }
    addLog("No compiler were found for "+filename);
    return -1;
}

string getParticipantName(string filename,int task)
{
    if (participantAsDir)
    {
        int i2=filename.find_last_of('\\');
        int i1=filename.find_last_of('\\',i2-1);
        if (i2-i1<0)
        {
            addLog("can't find username in "+filename);
            return "unknown";
        };
        string res="";
        res.insert(0,filename,i1+1,i2-i1-1);
        //addLog(res);
        return res;
    } else
    {
          int i=filename.find_last_of('\\');
          string onlyFilename="";
          onlyFilename.insert(0,filename,i+1,filename.size()-i);
          string res="";
          for (i=0;i<onlyFilename.size()&&i<tasks[task].mask.size();i++)
            if (tasks[task].mask[i]=='?') res+=onlyFilename[i];
          return res; 
    }
    return "unknown";
}

int findParticipantsWorks()
{
    //addLog("finding participants works");
    for (int i=0;i<tasks.size();i++)
    {
        FileList files;
        if (!TaskLocation.empty())
           files=findRecursive(TaskLocation,tasks[i].mask);
        else
            files=findRecursive(getCurrentDir(),tasks[i].mask);
        map<string,int> mySet;    
        for (int j=0;j<files.size();j++)
        {
            //addLog("find file "+files[j]);
            if (getCompiler(files[j])==-1) continue;

            ParticipantTask userTask(files[j]);
            userTask.taskNumber=i;
            userTask.compilerNumber=getCompiler(files[j]);
            string participantName=getParticipantName(files[j],i);
            if (mySet[participantName])
            {
               addLog("Error: participant "+participantName+" already have such a task: "+files[i]);
               continue;
            };
            mySet[participantName]=1;
            participants[participantName].push_back(userTask);
        }       
    }
    return 0;
}


int parseConfig()
{ 
    currentDir=getCurrentDir();
    skipCheckedPrograms=0;
    FileList configs;
    configs=findConfigs(getCurrentDir());
    if (configs.size()==0) 
    {
        addLog("No *.cfg file were found.");
        return 1;
    };
    string configFileName=configs[0];
    addLog("Finding config file "+configFileName);
    currentTaskName="";
    currentTaskNumber=-1;
    currentCompilerNumber=-1;
    parserState=PARS_TASKS;
    if (readConfigFile(configFileName)) return 1;
    makeTests();
    parserState=PARS_COMPILERS;
    //addLog("compil1");
    if (readConfigFile("compilers.conf")) 
    {
        addLog("File not Found: compilers.conf");
        return 1;
    };
    //addLog("compil2");
    return findParticipantsWorks();
}
