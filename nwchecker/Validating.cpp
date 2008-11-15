#include<iostream>
#include<string>
#include<fstream>
#include<ctype.h>

using namespace std;

extern string runDir;

unsigned long int runProgram(string name, int verbose=0);

char symbolCheck(string inFile, string juriFile)
{
	string inBinary="", juriBinary="";
	ifstream f((runDir+inFile).c_str());
	while (!f.eof())
		inBinary+=f.get();
	f.close();
	ifstream w((runDir+juriFile).c_str());
	while (!w.eof())
		juriBinary+=w.get();
	w.close();
	if (inBinary==juriBinary)
		return '+';
	string inText="", juriText="";
	for (unsigned int i=0; i<inBinary.size(); i++)
		if (!isspace(inBinary[i]))
			inText+=inBinary[i];
	for (unsigned int i=0; i<juriBinary.size(); i++)
		if (!isspace(juriBinary[i]))
			juriText+=juriBinary[i];
	if (inText==juriText)
		return 'P';
	return '-';
}

char validatingTask(string inFile, string outFile, string juriFile,
		string testProgram="")
{
	if (!testProgram.empty())
	{
		return (char) runProgram(runDir+testProgram+" "+inFile+" "+outFile+" "
				+juriFile);
	}
	else
		return symbolCheck(outFile, juriFile);
}
