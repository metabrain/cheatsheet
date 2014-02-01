BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
OutputStream out = new PrintStream(new BufferedOutputStream(System.out));
//or 
StringBuffer out = new StringBuffer(LARGE_CONST);
out.append(...);
out.flush();
out.close();