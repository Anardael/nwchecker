#include "Main.h"

map<string,vector<ParticipantTask> > participants;
map<string,SaveInformation> saving;
vector<Task> tasks;
vector <CompilerInfo> compilers;

string TaskLocation, runDir;// both have trailing slash
bool participantAsDir;
bool skipCheckedPrograms;
bool silentMode;

///////////////////////////////
int fileExists(string name);
int getFileTime(string fileName);
void clearDir(string dir, string exclude);
char validatingTask(string inFile, string outFile, string juriFile,
		string testProgram="");
string getCurrentDir();
string to_str(int a)
{
	char buff[1024];
	itoa(a, buff, 10);
	return string(buff);
}
string to_str(unsigned int a)
{
	char buff[1024];
	sprintf(buff, "%u", a);
	return string(buff);
}
string to_str(long unsigned int a)
{
	char buff[1024];
	sprintf(buff, "%lu", a);
	return string(buff);
}
string to_str(float a)
{
	char buff[1024];
	sprintf(buff, "%0.f", a);
	return string(buff);
}

///////////////////////////////
void checkProgram(ParticipantTask &task, string participantName)
{
	addLog("Cheking task number "+to_str(task.taskNumber+1)+". Participant "
			+participantName);
	if (skipCheckedPrograms)
	{
		unsigned int newTime=getFileTime(task.path.c_str());
		if (saving.find(task.path)!=saving.end())

		{
			SaveInformation sinfo=saving[task.path];

			if (newTime==sinfo.checkFileTime)
			{
				addLog("Task "+task.path+" was skipped");
				task.result = sinfo.result;
				task.score = sinfo.score;
				task.checkFileTime=newTime;
				return;
			}
		}
		task.checkFileTime=newTime;
	}
	CompilerInfo compiler=compilers[task.compilerNumber];
	Task tsk=tasks[task.taskNumber];
	clearDir(runDir, "");
	CopyFile(task.path.c_str(),(runDir+compiler.inFile).c_str(),0);
	SetCurrentDirectory(runDir.c_str());
	runProgram(compiler.exeFile + " " + compiler.params, 1);
	string exename=runDir+compiler.outFile;
	if (!fileExists(exename))
	{
		task.result="Compile failed";
		addLog("Compile Failed...");
		task.score=0;
		return;
	}
	clearDir(runDir, compiler.outFile);
	task.score=0;
	for (unsigned int i=0; i<tsk.tests.size(); i++)
	{
		CopyFile(tsk.tests[i].testFile.c_str(),(runDir+tsk.inFile).c_str(),0);
		addLog(participantName+": task "+to_str(task.taskNumber+1)+" test "
				+to_str(i+1)+" ", 0);
		char res=runProgramTimer(exename, tsk.tests[i].maxTime, 1);
		if (res!='+')
		{
			task.result+=res;
			continue;
		}
		if (!fileExists(runDir+tsk.outFile))
		{
			task.result+="F";
			addLog("No output File...");
			continue;
		}
		CopyFile(tsk.tests[i].judgeFile.c_str(),(runDir+"judge.sol1").c_str(),0);
		if (!tsk.checkProgram.empty())
		{
			CopyFile(tsk.checkProgram.c_str(),(runDir+"check.exe").c_str(),0);
			res=validatingTask(tsk.inFile, tsk.outFile, "judge.sol1",
					"check.exe");
		}
		else
			res=validatingTask(tsk.inFile, tsk.outFile, "judge.sol1");
		task.result+=res;
		if (res=='+')
			task.score+=tsk.tests[i].maxScore, addLog("Right answer!!! ");
		else if (res=='P')
			task.score+=tsk.tests[i].maxScore*tsk.wrongFormat,
					addLog("Presentation error.");
		else
			addLog("Wrong answer...");
		clearDir(runDir, compiler.outFile);
	}
	addLog("Task "+task.path+" Result: "+task.result+" score "
			+to_str(task.score));
	addLog("***********************************************************************");
}
void checkAll()
{
	map<string,vector<ParticipantTask> >::iterator i;
	for (i=participants.begin(); i!=participants.end(); i++)
	{
		addLog("Name "+i->first+" path "+i->second[0].path);
		//int t=i->second[0].taskNumber;
		for (unsigned int j=0; j<i->second.size(); j++)
			checkProgram(i->second[j], i->first);
	};

}
void saveResults()
{
	ofstream f("save.dat");
	map<string,vector<ParticipantTask> >::iterator i;
	for (i=participants.begin(); i!=participants.end(); i++)
		for (unsigned int j=0; j<i->second.size(); j++)
			f<<i->second[j].path<<endl<<i->second[j].result<<endl
					<< i->second[j].score<<endl
					<<getFileTime(i->second[j].path.c_str())<<endl;
	f.close();
}

void loadResults()
{
	ifstream f("save.dat");
	if (!f)
	{
		addLog("Save file not found");
		return;
	}
	while (!f.eof())
	{
		char buffer [1024];
		f.getline(buffer, 1024);
		string path(buffer);
		if (path=="")
			return;
		f.getline(buffer, 1024);
		SaveInformation info;
		info.result=string(buffer);
		f.getline(buffer, 1024);
		info.score=atof(buffer);
		f.getline(buffer, 1024);
		info.checkFileTime=atoi(buffer);
		saving[path]=info;
	}
	f.close();
	return;

}

void generateHTML()
{
	map<string,vector<ParticipantTask> >::iterator i;
	ofstream g("result.html");
	g<<"<html><head><title>Testing result</title></head><body><table border=1><tr><th>Name";
	for (unsigned int j=0; j<tasks.size(); j++)
		g<<"<th>"<<tasks[j].name<<"<th>&nbsp;";
	g<<"<td>Sum";
	for (i=participants.begin(); i!=participants.end(); i++)
	{
		g<<"<tr><td>"<<i->first;
		double sum=0;
		for (unsigned int k=0; k<tasks.size(); k++)
		{
			int tmp=0;
			for (unsigned int j=0; j<i->second.size(); j++)
				if (i->second[j].taskNumber==k)
				{
					g<<"<td>"<<i->second[j].result<<"<td>"<<i->second[j].score;
					sum+=i->second[j].score;
					tmp=1;
				}
			if (!tmp)
				g<<"<td>no file<td>&nbsp;";
		}
		g<<"<td>"<<sum;
	}
	g<<"</table></body></html>";
	g.close();
}

int main()
{
	addLog("===========================================================");
	addLog("NWChecker started");
	string curdir=getCurrentDir();
	loadResults();
	if (parseConfig())
		return 1;
	checkAll();
	SetCurrentDirectory(curdir.c_str());
	generateHTML();
	saveResults();
	if(silentMode) {
		int i;
		cin>>i;
	}
	return 0;
}
