# Introduction #

Please ask any questions to the project on this page.


# Details #

**Question**
How to match two fields in jsp? I need match Pass and ConfPass, but my Entity User
does not have field confirmPassword, because we don't need this field in our DB.

**Answer**
You need to add this confirm password parameter to your servlet. It is not necessary to put that into database objects


---


**Question**
How to load/upload file?

**Answer**
You could use servlet for downloading image from service. It could be like this:
```
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        //Get bytes somehow     
           if (bytes != null) {
                response.addHeader("Cache-Control", "max-age = 10000,public");
                Date expdate = new Date ();
                expdate.setTime (expdate.getTime() + (3600 * 1000*24*30));
                response.addHeader("Expires", expdate.toGMTString());
                response.addHeader("ETag", name);
            response.setContentLength(bytes.value.length);
            response.getOutputStream().write(bytes.value);
            
        }

    }
```
And you will need to add it into web.xml. For uploading file you could use this instruction.