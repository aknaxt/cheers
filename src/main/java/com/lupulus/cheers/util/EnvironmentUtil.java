package com.lupulus.cheers.util;

import java.util.Arrays;

public class EnvironmentUtil {
    
    
    private static final String ENV_MOCK_REPOSITORY = "mockRepository";

    
    private EnvironmentUtil() {
        
    } 

    /**
     * Checks if mock repository profile is active.
     *
     * @return true, if is mock repository profile active
     */
    public static boolean isMockRepositoryActive(String[] activeProfiles) {
        
        return containsProfile(activeProfiles, ENV_MOCK_REPOSITORY);
    }
    
    public static boolean containsProfile(String[] activeProfiles, String profile)
    {   	
    	return Arrays.stream(activeProfiles)
    			.anyMatch(
    			   env -> (env.equalsIgnoreCase(profile.trim())) 
    			 );
    }

}
