program juriv_01;
var a,b,c,p:extended;
begin
   assign(input, 'square.dat');
   reset(input);
   assign(output, 'square.sol');
   rewrite(output);
   read(a,b,c);
   if (a>=b+c) or (b>=a+c) or (c>=a+b) then begin writeln(0); close(output); exit; end;
   p:=(a+b+c)/2;
   p := p*(p-a)*(p-b)*(p-c);
   writeln(sqrt(p):0:3);
   close(output);
end.
