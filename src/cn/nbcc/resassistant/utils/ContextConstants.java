package cn.nbcc.resassistant.utils;

import java.net.*;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.eclipse.core.runtime.Platform;
import org.eclipse.osgi.service.datalocation.Location;

public class ContextConstants{
    //initailzed during class loading
    private static final ContextConstants INSTANCE = new ContextConstants();
    
    public static URI WORKSPACE_PATH = null;  
    public static String DATA_FOLDER ;
    
    static {
    	Location location = Platform.getInstallLocation();  
    	if (location != null) {  
    	    URL url = location.getURL();  
    	    try {
				WORKSPACE_PATH = url.toURI();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}  
    	}
    	
    	Path root = Paths.get(WORKSPACE_PATH);
    	DATA_FOLDER = root.resolve("data").toString();
    }
//    private static String WORKSPACE_PATH = "C:/workspace";
	public static final String UNKNOWN="<Пе>";

    
 
    //to prevent creating another instance of Singleton
    private ContextConstants(){}
 
    public static ContextConstants getInstance(){
        return INSTANCE;
    }
    
    public URI getWorkSpace() {
		return WORKSPACE_PATH;
	}
}

