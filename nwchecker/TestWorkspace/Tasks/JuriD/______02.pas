Program info;
 var b1,b2,b3,b4:LongInt;
     control:Integer;
     myControl,N,Sum,i:Longint;
Begin
 Assign(input,'info.dat');
 Reset(input);
 Assign(output,'info.sol');
 Rewrite(output);
 ReadLn(n);
 For i:=1 to N div 5 do
 Begin
  Read(b1,b2,b3,b4,control);
  myControl:=((b1*b2 mod 256)*(b3*b4 mod 256)mod 256);
  if (control=myControl) Then inc(sum,4);
 End;
 WriteLN(Sum);
 Close(input);
 Close(Output);
End.