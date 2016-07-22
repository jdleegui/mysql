package com.coweaver.test.test;

class ENUM_CONFIG {
	
	public static int MAX_REGION_ID	= 255	;
}

public class CConfig {

	private String id = null;
	private JdbcConfig cfg = null;
	
	public CRegion region_head = null;
	
	public CConfig(String uid, JdbcConfig jcfg) {
		// TODO_Auto-generated method stub
		id = uid;
		cfg = jcfg;
	}

	public boolean load() {
		// TODO_Auto-generated method stub
		boolean bReturn = false;
		if (cfg == null || id == null)
		{
			return bReturn; 
		}
		else
		{
			String section = "CONFIG";
			int region_count = cfg.Get(id, section, "COUNT", 0); 
			System.out.printf("%d\n", region_count);
			if (0 < region_count)
			{
				if((ENUM_CONFIG.MAX_REGION_ID+1) < region_count)
				{
					return bReturn;
				}
				
				CRegion reg = region_head;
				while(reg != null)
				{
					CRegion reg1 = reg;			
					reg = reg.GetNextRegion();
				}

				region_head = null;
		
				CRegion before = null;
				CRegion current = null;

				for(int nId = 0; nId < region_count; nId++)
				{
					String szSection1 = String.format("REGION%3.3u", nId);
		
					current = new CRegion(this);
//////				if(current->LoadRegion(pv, szSection1) == FALSE)
//////				{
//////					delete current;
//////					if(nId == 0)
//////					{
//////						SetDefault();
//////					}
//////					else
//////					{
//////						region_count = nId;
//////					}
//////					if (before != NULL) before->SetNextRegion(NULL);
//////					return bReturn;
//////				}
					if(before == null)
						region_head = current;
					else
						before.SetNextRegion(current);
						before = current;
				}
//////
//////			bReturn = TRUE;
////		}
//////
//////		return bReturn;
////		}
////	}
////
			}
		}
		
		return bReturn;
	}

}
