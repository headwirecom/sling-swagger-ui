package apps.swagger.apis;

import java.util.Iterator;
import java.util.ArrayList;

import javax.script.Bindings;
import javax.jcr.query.Query;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.scripting.sightly.pojo.Use;

public class Helper implements Use {

    private SlingHttpServletRequest request;
    
    public ArrayList<String> getOpenApiFiles() {
        ArrayList<String> files = new ArrayList<String>();
        
        Iterator<Resource> folders = request.getResourceResolver().findResources("SELECT * FROM sling:Folder WHERE openapi = true", Query.SQL);
        while(folders.hasNext()) {
            Resource folder = folders.next();
            Iterator<Resource> resources = folder.listChildren();
            while(resources.hasNext()) {
                Resource res = resources.next();
                if("nt:file".equals(res.getResourceType())) {
                    files.add(res.getPath());
                }
            }
        }
        return files;
    }
    
    public void init(Bindings bindings) {
        request = (SlingHttpServletRequest) bindings.get("request");
    }
}