#include <fstream>
#include <ctype.h>
#include <stdlib.h>
#include "Main.h"
#define PARS_TASKS 1
#define PARS_COMPILERS 2

int parserState=0;

FileList findFiles(string dir, string filemask);
FileList findConfigs(string dir);
FileList findRecursive(string dir, string filemask);
string currentTaskName;
int currentTaskNumber, currentCompilerNumber;
string mainDir;
string trim(const string& str, const string& drop = " ")
{
	string s=str;
	string r=s.erase(s.find_last_not_of(drop)+1);
	return r.erase(0, r.find_first_not_of(drop));
}

void addLog(string message)
{
	addLog(message, 1);
}
void addLog(string message, int en)
{
	cout<< message;
	if (en)
		cout<<endl;
	ofstream f((mainDir+"NWC.log").c_str(), ios::app);
	f<<message;
	if (en)
		f<<endl;
	f.close();
}
/**
 * Path transformation:
 * adds current dir if no dirve letter is set
 */
string path_transform(string path)
{
	addLog("path_transform input: "+path);
	if (path.length()<=1||path[1]!=':')
		path = mainDir + path;
	if (path[path.length()-1]!='\\'&&path[path.length()-1]!='/')
		path+='\\';
	addLog("path_transform output: "+path);
	return path;
}
string file_path_transform(string path)
{
	if (path.length()<=1||path[1]!=':')
		path = mainDir + path;
	return path;
}

void addTask(string taskName)
{
	unsigned int i=0;
	while (i<taskName.size()&&taskName[i]!='[')	i++;
	if (i>=taskName.size()){addLog("error in setting Task"+taskName);return;}
	string res="";
	i++;
	while (i<taskName.size()&&taskName[i]!=']'){res+=taskName[i];i++;}
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
void setValue(string lexem, string value)
{
	for (unsigned int i=0; i<lexem.size(); i++)
		lexem[i]=toupper(lexem[i]);

	if (parserState==PARS_TASKS)
	{
		if ("RUNDIR"==lexem)
		{
			runDir=path_transform(value);
			return;
		}
		if ("TASKLOCATION"==lexem)
		{
			TaskLocation=path_transform(value);
			return;
		}
		if ("PARTICIPANTASDIR"==lexem)
		{
			for (unsigned int i=0; i<value.size(); i++)
				value[i]=toupper(value[i]);
			if ("TRUE"==value)
				participantAsDir=1;
			else if ("FALSE"==value)
				participantAsDir=0;
			else
				addLog("Participant as Dir error. Not Bool "+value);
			return;
		}
		if ("SKIPCHECKEDPROGRAMS"==lexem)
		{
			for (unsigned int i=0; i<value.size(); i++)
				value[i]=toupper(value[i]);
			if ("TRUE"==value)
				skipCheckedPrograms=1;
			else if ("FALSE"==value)
				skipCheckedPrograms=0;
			else
				addLog("Participant as Dir error. Not Bool "+value);
			return;
		}
		if (currentTaskNumber==-1||(unsigned int)currentTaskNumber>=tasks.size())
		{
			addLog("No task defined. can't add lexem "+lexem+" with value "
					+value);
			return;
		}
		if ("INPUT"==lexem)
			tasks[currentTaskNumber].inFile=value;
		if ("OUTPUT"==lexem)
			tasks[currentTaskNumber].outFile=value;
		if ("INPREFIX"==lexem)
			tasks[currentTaskNumber].inPrefix=file_path_transform(value);
		if ("INSUFFIX"==lexem)
			tasks[currentTaskNumber].inSuffix=value;
		if ("OUTSUFFIX"==lexem)
			tasks[currentTaskNumber].outSuffix=value;
		if ("OUTPREFIX"==lexem)
			tasks[currentTaskNumber].outPrefix=file_path_transform(value);
		if ("TESTS"==lexem)
			tasks[currentTaskNumber].testsStr=value;
		if ("SCORE"==lexem)
			tasks[currentTaskNumber].scoreStr=value;
		if ("MASK"==lexem)
			tasks[currentTaskNumber].mask=value;
		if ("TIME"==lexem)
			tasks[currentTaskNumber].timeStr=value;
		if ("CHECKPROGRAM"==lexem)
			tasks[currentTaskNumber].checkProgram=file_path_transform(value);
		if ("WRONGFORMAT"==lexem)
			tasks[currentTaskNumber].wrongFormat=atof(value.c_str());
	}
	if (parserState==PARS_COMPILERS)
	{
		if (currentCompilerNumber==-1||(unsigned int)currentCompilerNumber>=compilers.size())
		{
			addLog("No compiler defined. can't add lexem "+lexem+" with value "
					+value);
			return;
		}
		if ("EXTENSION"==lexem)
		{
			for (unsigned int i=0; i<value.size(); i++)
				value[i]=toupper(value[i]);
			compilers[currentCompilerNumber].extension=value;
		}
		if ("EXEFILE"==lexem)
			compilers[currentCompilerNumber].exeFile=file_path_transform(value);
		if ("INFILE"==lexem)
			compilers[currentCompilerNumber].inFile=value;
		if ("OUTFILE"==lexem)
			compilers[currentCompilerNumber].outFile=value;
		if ("PARAMS"==lexem)
			compilers[currentCompilerNumber].params=value;
	}
}

void decodeLine(string line)
{
	if (line.empty())
		return;
	unsigned int i=0;
	while (i<line.size()&&isspace(line[i]))i++;
	if (i>=line.size())return;
	if (line[i]=='#')return;
	if (line[i]=='['){addTask(line);return;}
	string lexemName="", lexemVal="";
	while (i<line.size()&&line[i]!='='){lexemName+=line[i];i++;}
	if (i>=line.size()){addLog("error in decode line"+line);return;}
	i++;
	lexemVal.insert(0, line, i, line.size()-i);
	lexemName=trim(lexemName);
	lexemVal=trim(lexemVal);
	setValue(lexemName, lexemVal);

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
		f.getline(buffer, 1024);
		decodeLine(buffer);
	}
	f.close();
	return 0;
}
void makeTests()
{

	for (unsigned int i=0; i<tasks.size(); i++)
	{
		unsigned int testsOffset=0, scoresOffset=0, timesOffset=0;
		while (testsOffset<tasks[i].testsStr.size()&&scoresOffset
				<tasks[i].scoreStr.size() &&scoresOffset
				<tasks[i].scoreStr.size())
		{
			string curTest="";
			while (testsOffset<tasks[i].testsStr.size()
					&& isspace(tasks[i].testsStr[testsOffset]))
				testsOffset++;
			while (testsOffset<tasks[i].testsStr.size()
					&& !isspace(tasks[i].testsStr[testsOffset]))
			{
				curTest+=tasks[i].testsStr[testsOffset];
				testsOffset++;
			}
			string curScore="";
			while (scoresOffset<tasks[i].scoreStr.size()
					&& isspace(tasks[i].scoreStr[scoresOffset]))
				scoresOffset++;
			while (scoresOffset<tasks[i].scoreStr.size()
					&& !isspace(tasks[i].scoreStr[scoresOffset]))
			{
				curScore+=tasks[i].scoreStr[scoresOffset];
				scoresOffset++;
			}
			string curTime="";
			while (timesOffset<tasks[i].timeStr.size()
					&& isspace(tasks[i].timeStr[timesOffset]))
				timesOffset++;
			while (timesOffset<tasks[i].timeStr.size()
					&& !isspace(tasks[i].timeStr[timesOffset]))
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
	for (unsigned int i=0; i<filename.length(); i++)
		filename[i]=toupper(filename[i]);
	for (unsigned int i=0; i<compilers.size(); i++)
	{
		string part="."+compilers[i].extension;
		if (filename.find(part)!=string::npos)
			return i;
	}
	addLog("No compiler were found for "+filename);
	return -1;
}

string getParticipantName(string filename, int task)
{
	if (participantAsDir)
	{
		int i2=filename.find_last_of('\\');
		int i1=filename.find_last_of('\\', i2-1);
		if (i2-i1<0)
		{
			addLog("can't find username in "+filename);
			return "unknown";
		};
		string res="";
		res.insert(0, filename, i1+1, i2-i1-1);
		//addLog(res);
		return res;
	}
	else
	{
		unsigned int i=filename.find_last_of('\\');
		string onlyFilename="";
		onlyFilename.insert(0, filename, i+1, filename.size()-i);
		string res="";
		for (i=0; i<onlyFilename.size()&&i<tasks[task].mask.size(); i++)
			if (tasks[task].mask[i]=='?')
				res+=onlyFilename[i];
		return res;
	}
	return "unknown";
}

int findParticipantsWorks()
{
	addLog("Locating participants' works");
	for (unsigned int i=0; i<tasks.size(); i++)
	{
		FileList files;
		addLog(tasks[i].mask);
		if (!TaskLocation.empty())
			files=findRecursive(TaskLocation, tasks[i].mask);
		else
			files=findRecursive(getCurrentDir()+'\\', tasks[i].mask);
		map<string,int> mySet;
		for (unsigned int j=0; j<files.size(); j++)
		{
			string fileFound = files[j];
			//addLog("Found file "+fileFound);
			if (getCompiler(files[j])==-1) {
				addLog("Cannot find compiler for file "+fileFound);
				continue;
			}

			ParticipantTask userTask(fileFound);
			userTask.taskNumber=i;
			userTask.compilerNumber=getCompiler(fileFound);
			string participantName=getParticipantName(fileFound, i);
			if (mySet[participantName])
			{
				addLog("Error: participant "+participantName
						+" already have such a task: "+fileFound);
				continue;
			}
			addLog(participantName+" found file: "+fileFound);
			mySet[participantName]=1;
			participants[participantName].push_back(userTask);
		}
	}
	return 0;
}

int parseConfig()
{
	mainDir=getCurrentDir()+"\\";
	skipCheckedPrograms=0;
	FileList configs;
	configs=findConfigs(getCurrentDir());
	if (configs.size()==0)
	{
		addLog("No *.conf files were found.");
		return 1;
	}
	string configFileName=configs[0];
	addLog("Found configuration file "+configFileName);
	currentTaskName="";
	currentTaskNumber=-1;
	currentCompilerNumber=-1;
	parserState=PARS_TASKS;
	if (readConfigFile(configFileName))
		return 1;
	addLog(configFileName + " was read successfully");
	makeTests();
	parserState=PARS_COMPILERS;
	if (readConfigFile("compilers.conf"))
	{
		addLog("File not Found: compilers.conf");
		return 1;
	}
	addLog("Compiler configuration was read successfully");
	return findParticipantsWorks();
}
