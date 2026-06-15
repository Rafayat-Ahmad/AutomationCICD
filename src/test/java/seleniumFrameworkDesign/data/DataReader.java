package seleniumFrameworkDesign.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	
	
	@SuppressWarnings("deprecation")
	public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
		
//		read json to string
		String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "\\src\\test\\java\\seleniumFrameworkDesign\\data\\purchaseOrder.json"),
				StandardCharsets.UTF_8);
		
//		String to hashmap
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
		});
//		data is list with values as 2 hashmaps
		System.out.println("Total test data rows: " + data.size());
		return data;
	}

}
