import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import nl.knaw.dans.common.dbflib.CorruptedTableException;
import nl.knaw.dans.common.dbflib.Field;
import nl.knaw.dans.common.dbflib.IfNonExistent;
import nl.knaw.dans.common.dbflib.Record;
import nl.knaw.dans.common.dbflib.Table;


public class db_reader {
	private static String MultiMeterPost = "C:\\sd_app\\sd_app1\\db\\MultiMeterPost.dbf";
	private static String SingleHeadMeters = "C:\\sd_app\\sd_app1\\db\\SingleHeadMeters.dbf";
	public static void main(String[] args) 
	{
		db_reader dbReader  = new db_reader(); 
		System.out.println("sa info 1 main:         " );
	    System.out.println("sa info 2 main:         " );
		dbReader.openAndReaddbf(MultiMeterPost);
		System.out.println("sa info 3:         " );
	    System.out.println("sa info 4:         " );
	    dbReader.openAndReaddbf(SingleHeadMeters);
		System.out.println("sa info 5:         " );
	    System.out.println("sa info 6:         " );
	}
	
	void openAndReaddbf(String sPath){
		
		
	System.out.println("sa info 1:         " +sPath.toString());
	Table table = new Table(new File(sPath.toString()));
	System.out.println("sa info 1:         " );
    System.out.println("sa info 2:         " );

	try
	{
		System.out.println("sa info 3:         " );
	    System.out.println("sa info 4:         " );
	    try {
	    	System.out.println("sa info 5:         " );
	        System.out.println("sa info 6:         " );
			table.open(IfNonExistent.ERROR);
			List<Field> fields = table.getFields();

			for (final Field field : fields)
			{
			    System.out.println("Name:         " + field.getName());
			    System.out.println("Type:         " + field.getType());
			    System.out.println("Length:       " + field.getLength());
			    System.out.println("DecimalCount: " + field.getDecimalCount());
			    System.out.println();
			}
			Iterator<Record> iterator = table.recordIterator();
			
			if (sPath.compareTo(SingleHeadMeters) == 0)
			{
				try {
					Sqlite.sqliteDBCreator("SingleHeadMeters");
				}catch (Exception e) {
						// TODO Auto-generated catch block
					e.printStackTrace();
				}
				while (iterator.hasNext())
				{
					System.out.println("Here 1 :) " );
					Record record = iterator.next();
					Number nvObjID  = record.getNumberValue("OBJECTID_1");
					// Convert to a primitive before comparing
					if (nvObjID != null )
					{
					    System.out.println("ObjID     : " + nvObjID.intValue());
					}
					
					Number nvPostId  = record.getNumberValue("PostId");
					// Convert to a primitive before comparing
					if (nvObjID != null )
					{
					    System.out.println("PostId     : " + nvPostId.intValue());
					}
					
					String nvNote  = record.getStringValue("Note");
					
					// Convert to a primitive before comparing
					if (nvNote != null )
					{
					    System.out.println("Note      :" + nvNote.toString());
					}
					
					Number nvMeterId  = record.getNumberValue("MeterId");
					// Convert to a primitive before comparing
					if (nvMeterId != null )
					{
					    System.out.println("MeterId     : " + nvMeterId.intValue());
					}
					
					
					Number nvSHAPE_fid  = record.getNumberValue("SHAPE_fid");
					// Convert to a primitive before comparing
					if (nvSHAPE_fid != null )
					{
					    System.out.println("SHAPE_fid     : " + nvSHAPE_fid.intValue());
					}
					
					Number nvLat  = record.getNumberValue("Lat");
					// Convert to a primitive before comparing
					if (nvLat != null )
					{
					    System.out.println("Lat       : " + nvLat.floatValue());
					}
					Number nvLon  = record.getNumberValue("Long");
					// Convert to a primitive before comparing
					if (nvLon != null )
					{
					    System.out.println("Lon       : " + nvLon.floatValue()+ "\n\n");
					}
					
					Sqlite.sqliteAddBatch(nvObjID.intValue(), nvPostId.intValue(), null,
							nvMeterId.intValue(), nvSHAPE_fid.intValue(), nvLat.floatValue(), nvLon.floatValue());
					
				}
			}
			else if (sPath.compareTo( MultiMeterPost) == 0)
			{
				try {
					Sqlite.sqliteDBCreator("MultiMeterPost");
				}catch (Exception e) {
						// TODO Auto-generated catch block
					e.printStackTrace();
				}
				while (iterator.hasNext())
				{
					Record record = iterator.next();
					Number nvObjID  = record.getNumberValue("OBJECTID");
					// Convert to a primitive before comparing
					if (nvObjID != null )
					{
					    System.out.println("ObjID     : " + nvObjID.intValue());
					}
					
					String nvBlockName  = record.getStringValue("BlockName : ");
					
					// Convert to a primitive before comparing
					if (nvBlockName != null )
					{
					    System.out.println("BlockName" + nvBlockName.toString());
					}
					
					String nvTerminal_I  = record.getStringValue("Terminal_I");
					// Convert to a primitive before comparing
					if (nvTerminal_I != null )
					{
					    System.out.println("Terminal_I: " + nvTerminal_I.toString());
					}
					 Number nvLat  = record.getNumberValue("Lat");
					// Convert to a primitive before comparing
					if (nvLat != null )
					{
					    System.out.println("Lat       : " + nvLat.floatValue());
					}
					Number nvLon  = record.getNumberValue("Long");
					// Convert to a primitive before comparing
					if (nvLon != null )
					{
					    System.out.println("Lon       : " + nvLon.floatValue()+ "\n\n");
					}
					
					Sqlite.sqliteAddBatch(nvObjID.intValue(), null, nvTerminal_I.toString(),
											nvLat.floatValue(), nvLon.floatValue());
					
				}
			}
			
		} catch (CorruptedTableException e) {
			// TODO Auto-generated catch block
			System.out.println("sa info 7:         " );
	        System.out.println("sa info 8:         " );
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("sa info 9:         " );
	        System.out.println("sa info 10:         " );
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("sa info 11:         " );
	        System.out.println("sa info 12:         " );
			e.printStackTrace();
		}
		//Sqlite.sqlitePrintDB();
	    // ... do your stuff

	} catch (Exception e) {
		// TODO Auto-generated catch block
		System.out.println("sa info 13:         " );
        System.out.println("sa info 14:         " );
		e.printStackTrace();
	}
	finally
	{
		System.out.println("sa info 7:         " );
	    System.out.println("sa info 8:         " );
	    try {
			table.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  // don't forget to close it!
	}
	
	}
	
}


