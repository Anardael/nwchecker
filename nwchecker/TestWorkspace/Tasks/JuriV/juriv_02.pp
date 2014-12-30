program juriv_02;
var r,i,n,a,b,c,d,s:longint;
begin
   assign(input, 'info.dat');
   reset(input);
   assign(output, 'info.sol');
   rewrite(output);
   read(n);
   r:=0;
   for i := 1 to n div 5 do
   begin
      read(a,b,c,d,s);
      if s=((a*b*c) mod 256)*d mod 256 then inc(r,4);
   end;
   writeln(r);
   close(output);
end.