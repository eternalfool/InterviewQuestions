import com.agoda.utils.DownloadUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.io.IOException;

class BaseTest {

    private static String TEST_DIR;

    @BeforeAll
    static void initAll() throws IOException {
        TEST_DIR = DownloadUtils.getTempDirByOS() + "MultiDownloaderTest/";
    }

    @BeforeEach
    void init() {
    }

    @AfterEach
    void destroy() {
        deleteDirectory(new File(TEST_DIR));
    }

    String getTestDir() {
        return TEST_DIR;
    }

    boolean deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return directoryToBeDeleted.delete();
    }
}
