package Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.uni.sofia.fmi.StringSearch;

public class GetTextFilesTest {

	List<File> fileList = StringSearch.getTextFiles(new File("E:/TestBooks"));

	@Test
	public void notEmptyList() {
		assertFalse(fileList.isEmpty());
	}

	@Test
	public void allFilesEndWithTxt() {
		boolean allText = true;
		for (File file : fileList) {
			if (!file.getName().endsWith(".txt")) {
				allText = false;
			}
		}
		assertTrue(allText);
	}

}
