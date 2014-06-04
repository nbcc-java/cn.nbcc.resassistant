package cn.nbcc.resassistant.utils;
public class ContextConstants{
    //initailzed during class loading
    private static final ContextConstants INSTANCE = new ContextConstants();
    private static String WORKSPACE_PATH = "C:/workspace";
    
 
    //to prevent creating another instance of Singleton
    private ContextConstants(){}
 
    public static ContextConstants getSingleton(){
        return INSTANCE;
    }
    
    public String getWorkSpace() {
		return WORKSPACE_PATH;
	}
}

