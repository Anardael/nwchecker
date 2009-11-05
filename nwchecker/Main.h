#include<string>
#include<vector>
#include<iostream>
#include<map>
#include<fstream>
///////////////////////////////
#include<windows.h>
#include<winbase.h>
///////////////////////////////
using namespace std;
typedef vector<string> FileList;
struct Test
{
	string testFile, judgeFile;
	unsigned int maxTime;
	float maxScore;
};
struct Task
{
	string inFile, outFile, checkProgram, name, mask;
	float wrongFormat;
	vector<Test> tests;
	Task():mask("*"), wrongFormat(.75f){}
	string inSuffix, inPrefix, testsStr, scoreStr, outSuffix, outPrefix, timeStr;
};
struct Perferences
{
	bool participantAsDir;
	string rundir;
};
struct CompilerInfo
{
	string extension, exeFile, inFile, outFile, name, params;
};
struct ParticipantTask
{
	string path, result;
	int checkFileTime;
	float score;
	unsigned int taskNumber, compilerNumber;
	ParticipantTask(string _path) :	path(_path), checkFileTime(0){}
};
struct SaveInformation
{
	string result;
	unsigned int checkFileTime;
	float score;
};
///////////////////////////////
unsigned long int runProgram(string, int);
char runProgramTimer(string, unsigned int, int);
int parseConfig();
void addLog(string message);
void addLog(string message, int);
string to_str(float a);
string to_str(int a);
string to_str(unsigned int a);
string to_str(long unsigned int a);
///////////////////////////////
//external variables
extern map<string,vector<ParticipantTask> > participants;
extern vector<Task> tasks;
extern string TaskLocation, runDir;
extern vector <CompilerInfo> compilers;
extern bool participantAsDir;
extern bool skipCheckedPrograms;
extern bool silentMode;
//external functions
string getCurrentDir();
FileList findFiles(string dir, string filemask);
FileList findConfigs(string dir);
FileList findRecursive(string dir, string filemask);
