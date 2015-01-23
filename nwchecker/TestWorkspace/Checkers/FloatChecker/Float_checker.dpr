program Float_checker;

{$APPTYPE CONSOLE}

uses
  SysUtils;
var f,w:TextFile;
    a,b:Real;
begin
  AssignFile(f,ParamStr(2));
  Reset(f);
  AssignFile(w,ParamStr(3));
  Reset(w);
  try
    try
     Read(f,a);
     Read(w,b);
     if (abs(a-b)<0.003) Then Halt(Ord('+')) Else Halt(Ord('-'));
    except
      Halt(Ord('-'));
    end;
  finally
    CloseFile(f);
    CloseFile(w);
  end;
end.
 