program juriv_04;
type integer=longint;
const
      maxn = 100000;
      maxch = maxn div 2;
var tree:array[1..maxn] of record deep,left,right:integer;end;
    ch:array[1..maxch] of record i,deep:integer; end;
    ch1,ch2:integer;
    i,n,a,b,c,m:integer;
procedure put(i,d:integer);
begin
   inc(ch1);
   if ch1>maxch then ch1 := 1;
   ch[ch1].i:=i;
   ch[ch1].deep:=d;
   tree[i].deep:=d;
end;
procedure get(var i,d:integer);
begin
   inc(ch2);
   if ch2>maxch then ch2 := 1;
   i := ch[ch2].i;
   d := ch[ch2].deep;
end;
begin
   assign(input, 'tree.dat');
   reset(input);
   assign(output,'tree.sol');
   rewrite(output);
   read(n,m);
   fillchar(tree,sizeof(tree),0);
   for i := 1 to m do
   begin
      read(a,b,c);
      tree[a].left := b;
      tree[a].right := c;
   end;
   ch1 := 0;ch2 := 0;
   put(1,1);
   while ch1 <> ch2 do
   begin
      get(a,b);{a-index, b-deep}
      inc(b);
      if tree[a].left<>0 then put(tree[a].left,b);
      if tree[a].right<>0 then put(tree[a].right,b);
   end;
   for i := 1 to n do
      writeln(tree[i].deep);
   close(output);
end.