program juriv_03;
uses math;
var a,l,s,k:extended;
begin
   assign(input, 'goat.dat');
   reset(input);
   assign(output, 'goat.sol');
   rewrite(output);
   read(a,l);
   if l<a/2 then s :=pi*l*l else
   if l>a*sqrt(2)/2 then s := a*a else
   begin
      k := sqrt(l*l-(a/2)*(a/2));
      s := a*k*2+2*l*l*(pi/2-2*arccos(a/2/l));
   end;
   writeln(s:0:3);
end.