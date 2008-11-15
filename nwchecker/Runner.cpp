#include"Main.h"
#include<windows.h>
#include<winbase.h>
#include<string.h>


void outErrorMessage(){
    LPVOID lpMsgBuf;
    unsigned long int i;         
    FormatMessage( 
        FORMAT_MESSAGE_ALLOCATE_BUFFER | FORMAT_MESSAGE_FROM_SYSTEM,
        NULL,
        i=GetLastError(),
        MAKELANGID(LANG_NEUTRAL, SUBLANG_DEFAULT), // Default language
        (LPTSTR) &lpMsgBuf,
        0,
        NULL 
    );
    if(!i)return;
    
    // Display the string.
    addLog((char*)lpMsgBuf);
    
    // Free the buffer.
    LocalFree( lpMsgBuf );     
}


unsigned long int runProgram(string name,int verbose=0){
    STARTUPINFO sinf;
    PROCESS_INFORMATION pinf;
    memset(&sinf,0,sizeof(sinf));
    sinf.cb=sizeof(sinf);
    sinf.wShowWindow=SW_SHOWMINIMIZED;
    sinf.dwFlags=STARTF_USESHOWWINDOW;
    char*s=new char[name.length()+1];
    memset(s,0,name.length()+1);
    strcpy(s,name.c_str());
    unsigned long int res,timer=GetTickCount();
    CreateProcess(NULL,s,NULL,NULL,0,CREATE_NEW_CONSOLE|NORMAL_PRIORITY_CLASS,NULL,NULL,&sinf,&pinf);
    while(WaitForSingleObject(pinf.hProcess,0)==WAIT_TIMEOUT);
    GetExitCodeProcess(pinf.hProcess,&res);
    if (verbose)addLog("Running "+name+" Time took: "+to_str(GetTickCount()-timer)+" Return status: "+to_str(res));
    return res;
}

char runProgramTimer(string name,int time,int verbose){
     addLog("with time "+to_str(time)+" ",0); 
    //cout<<"Running "<<name<<" with time "<<time<<" "; 
    STARTUPINFO sinf;
    PROCESS_INFORMATION pinf;
    memset(&sinf,0,sizeof(sinf));
    sinf.cb=sizeof(sinf);
    sinf.wShowWindow=SW_SHOWMINIMIZED;
    sinf.dwFlags=STARTF_USESHOWWINDOW;
    char*s=new char[name.length()+1];
    memset(s,0,name.length()+1);
    strcpy(s,name.c_str());
    unsigned long int res,timer=GetTickCount();
    GetLastError();
    CreateProcess(NULL,s,NULL,NULL,0,CREATE_NEW_CONSOLE|NORMAL_PRIORITY_CLASS,NULL,NULL,&sinf,&pinf);
    //outErrorMessage();
    FILETIME crt,ext,krnl,usr;
    GetProcessTimes(pinf.hProcess, &crt, &ext, &krnl, &usr);
    do{
         GetProcessTimes(pinf.hProcess, &crt, &ext, &krnl, &usr);
         Sleep(10);
    }while((ext.dwLowDateTime==0)&&(usr.dwLowDateTime/10000<=time)&&(GetTickCount()-timer<time*2));
    if(GetTickCount()-timer>=time*2){
        TerminateProcess(pinf.hProcess,0);
        if(verbose) addLog("W||smthing bad");
        return 'W';
    }else if(ext.dwLowDateTime==0){
        TerminateProcess(pinf.hProcess,0);
        if(verbose) addLog("TLE. Time: "+to_str(usr.dwLowDateTime/10000));
        return 'T';
    }else{
         GetExitCodeProcess(pinf.hProcess,&res);
         if(res==STILL_ACTIVE){
             TerminateProcess(pinf.hProcess,0);
             if(verbose) addLog("TLE (2). Time: "+to_str(usr.dwLowDateTime/10000));
             return 'T';
         }else if(res!=0){
             if(verbose) addLog("RE "+to_str(res)+" Time: "+to_str(usr.dwLowDateTime/10000));
             return 'R';
         }
    }
    if(verbose) addLog("OK. Time: "+to_str(usr.dwLowDateTime/10000)+" ",0);
    return '+';
    
}
