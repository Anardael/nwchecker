{$A+,B-,D+,E+,F-,G-,I+,L+,N-,O-,P-,Q+,R+,S+,T-,V+,X+}
{$M 1024,0,655360}
Program tree;
 TYpe mas=array [0..1000] of Word;
      masb=array [0..1000] of LonGint;
 Var
     n,c,p,l,r,i:LongInt;
     way:Array [0..100] of ^mas;
     from,stack:Array [0..100] of ^masb;
 Function getWay(n:LongInt):Word;
 Begin
  getWay:=way[n div 1000]^[n mod 1000];
 End;
 Procedure setWay(n,value:LongInt);
 Begin
  way[n div 1000]^[n mod 1000]:=value;
 End;
 Function getFrom(n:LongInt):LongInt;
 Begin
  getFrom:=from[n div 1000]^[n mod 1000];
 End;
 Procedure setFrom(n,value:LongInt);
 Begin
  from[n div 1000]^[n mod 1000]:=value;
 End;
 Function getStack(n:LongInt):LongInt;
 Begin
  getStack:=stack[n div 1000]^[n mod 1000];
 End;
 Procedure setStack(n,value:LongInt);
 Begin
  stack[n div 1000]^[n mod 1000]:=value;
 End;

 Procedure Resolve(k:LongInt);
 Var
  st,off:Word;
  i:Word;
 Begin
  st:=0;
  while getFrom(k)<>1 do
   Begin
    inc(st);
    setStack(st,k);
    k:=getFrom(k);
   End;
  off:=getway(k);
  For i:=st downto 1 do
   Begin
    setway(getStack(i),off+st-i+1);
    setFrom(getStack(i),1);
   End;
 End;


 Begin
  Assign(input,'tree.dat');
  reset(input);
  Assign(output,'tree.sol');
  rewrite(output);
  ReadLn(n,c);
  For i:=0 to n div 1000 do
  Begin
   New(way[i]);
   FillChar(way[i]^,sizeOf(mas),0);
   new(from[i]);
   FillChar(from[i]^,sizeOf(masb),0);
  End;
  For i:=0 to (n+10) div 2000 do
   New(stack[i]);
  For i:=1 to c Do
  Begin
   ReadLn(p,l,r);
   setWay(l,2);
   setWay(r,2);
   setFrom(l,p);
   setFrom(r,p);
  End;
  setFrom(1,1);
  setway(1,1);
  For i:=1 to n do
   Begin
    if getFrom(i)<>1 then resolve(i);
    Writeln(getWay(i));
   End;
  For i:=0 to n div 1000 do
  Begin
   Dispose(way[i]);
   Dispose(from[i]);
  End;
  For i:=0 to (n+10) div 2000 do
   dispose(stack[i]);

  Close(input);
  Close(output);
 End.