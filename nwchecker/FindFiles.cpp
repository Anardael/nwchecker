#include <vector>
#include <string>
#include "Main.h"
#include <dirent.h>
#include<sys/stat.h>
using namespace std;
string getCurrentDir()
{
	char buffer[200];
	char *res=getcwd(buffer, 200);
	return res;

}
int machMask(string file, string mask)
{
	//addLog(file+"-"+mask);
	for (unsigned int i=0; i<file.size()&&i<mask.size(); i++)
		if (toupper(file[i])!=toupper(mask[i])&&mask[i]!='?')
			return 0;
	return 1;
}
int isDir(string Path)
{
	DIR* dir=opendir(Path.c_str());
	if (dir)
	{
		return 1;
		closedir(dir);
	}
	else
		return 0;
}
;
FileList findFiles(string dir, string filemask)
{
	FileList res;
	DIR* td=opendir(dir.c_str());
	dirent*p;
	if (!td)
		return res;
	while (p=readdir(td))
	{
		string dirname=p->d_name;
		if (!isDir(dirname)&&machMask(dirname, filemask))
			res.push_back(dir+'\\'+dirname);
	}
	closedir(td);
	return res;
}
FileList findConfigs(string dir)
{
	FileList res;
	DIR* td=opendir(dir.c_str());
	dirent*p;
	string filemask=".cfg";
	if (!td)
		return res;
	while (p=readdir(td))
	{
		string dirname=p->d_name;
		if (!isDir(dirname)&&dirname.find(filemask)!=string::npos)
			res.push_back(dir+'\\'+dirname);
	}
	closedir(td);
	return res;
}

FileList findRecursive(string dir, string filemask)
{
	FileList res;
	DIR* td=opendir(dir.c_str());
	dirent* p;
	if (!td)
		return res;
	while (p=readdir(td))
	{
		string dirname=p->d_name;
		if (dirname=="."||dirname=="..")
			continue;
		//addLog(dirname); //Debugging search
		if (!isDir(dir+'\\'+dirname)&&machMask(dirname, filemask))
			res.push_back(dir+'\\'+dirname);
		else if (isDir(dir+'\\'+dirname))
		{
			FileList rec=findRecursive(dir+'\\'+dirname, filemask);
			for (unsigned int i=0; i<rec.size(); i++)
				res.push_back(rec[i]);
		}
	}
	closedir(td);
	return res;
}
int fileExists(string name)
{
	FILE*f=fopen(name.c_str(), "r");
	if (f)
	{
		fclose(f);
		return 1;
	}
	return 0;
}
void clearDir(string dir, string exclude)
{//excludes only one filename
	DIR* td=opendir(dir.c_str());
	dirent*p;
	if (!td)
		return;
	for (unsigned int i=0; i<exclude.size(); i++)
		exclude[i]=toupper(exclude[i]);
	while (p=readdir(td))
	{
		string tmp(p->d_name);
		for (unsigned int i=0; i<tmp.size(); i++)
			tmp[i]=toupper(tmp[i]);
		if (tmp!=exclude)
			DeleteFile(p->d_name);
	}
	closedir(td);
	return;
}

unsigned int getFileTime(string fileName)
{
	struct stat buf;
	int result;
	result=stat(fileName.c_str(), &buf);
	if (result)
		return 0;
	return buf.st_mtime;
}
