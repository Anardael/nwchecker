Program Square;
var a,b,c,p:Real;
Begin
 Assign(input,'square.dat');
 Reset(input);
 Read(a,b,c);
 Close(input);
 p:=(a+b+c)/2;
 Assign(output,'square.sol');
 Rewrite(output);
 if (p-a>0)and(p-b>0)and (p-c>0) Then
  WriteLn(sqrt(p*(p-a)*(p-b)*(p-c)):0:3) Else WriteLN(0);
 Close(output);
End.