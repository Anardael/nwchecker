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
       string testFile,judgeFile;
       int maxTime;
       float maxScore;      
};
struct Task
{
       string inFile,outFile,checkProgram,name,mask;
       float wrongFormat;
       vector<Test> tests;
       Task(): checkProgram(""),wrongFormat(.75f),mask("*"){};
       string inSuffix,inPrefix,testsStr,scoreStr,outSuffix,outPrefix,timeStr;
};
struct Perferences{
    bool participantAsDir;
    string rundir;       
};
struct CompilerInfo
{
    string extension,exeFile,inFile,outFile,name;
};
struct ParticipantTask
{
  string path,result;
  int checkFileTime;
  float score;
  int taskNumber,compilerNumber;
  ParticipantTask(string _path): result(""),score(0),path(_path),checkFileTime(0){};     
};
struct SaveInformation
{
  string result;
  int checkFileTime;
  float score; 
};
///////////////////////////////
unsigned long int runProgram(string,int);
char runProgramTimer(string,int,int);
int parseConfig();
void addLog(string message);
void addLog(string message,int);
string to_str(float a);
string to_str(int a);
string to_str(unsigned long int a);

///////////////////////////////
