Program goat;
 Var a,r:real;
Begin
assign(input,'goat.dat');
reset(input);
assign(output,'goat.sol');
rewrite(output);
read(a,r);
If r> sqrt(2)*a Then WriteLn(a*a)
Else
 if r<=a/2 then writeln(r*pi*r) else
 begin
  a:=a/2;
  writeln(r*pi*r-4*(r*r*arctan(sqrt(r*r-a*a)/a)-a*sqrt(r*r-a*a)));
 end;
close(input);
close(output);
End.