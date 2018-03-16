package monitorfolder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author nandashree.r
 *
 */
public class FolderHealthMonitor extends TimerTask {

	private static long TIMER;

	private long time;

	private long currentTime;

	private String secured;

	private String archived;

	private int countArchivedFiles;

	private int countDeletedFiles;

	FolderHealthMonitor(String path) {
		path += File.separator;
		this.secured = path + "secured";
		this.archived = path + "archived";
	}

	public long getFileSize(File folder) {

		long foldersize = 0;
		File[] filelist = folder.listFiles();

		for (int i = 0; i < filelist.length; i++) {

			if (filelist[i].isDirectory()) {
				foldersize += this.getFileSize(filelist[i]);
			} else {
				foldersize += filelist[i].length();
			}
		}
		return foldersize;
	}

	public void archiveAndMoveFile(File originalFile) {

		try {
			String filepath = this.archived + File.separator + originalFile.getName();
			originalFile.renameTo(new File(filepath));

			String zipFile = filepath + ".zip";
			FileOutputStream fos = new FileOutputStream(zipFile);
			ZipOutputStream zipOutputStream = new ZipOutputStream(fos);
			ZipEntry zipEntry = new ZipEntry(originalFile.getName());
			zipOutputStream.setLevel(Deflater.BEST_COMPRESSION);
			zipOutputStream.putNextEntry(zipEntry);
			FileInputStream fileInputStream = new FileInputStream(filepath);
			int len;
			byte[] buffer = new byte[1024];
			while ((len = fileInputStream.read(buffer)) > 0) {
				zipOutputStream.write(buffer, 0, len);
			}
			fileInputStream.close();
			zipOutputStream.closeEntry();
			zipOutputStream.close();
			this.countArchivedFiles++;
			System.out.println("Archived file: " + filepath);
			new File(filepath).delete();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void checkFileTimestamp(File folder) throws IOException {

		File[] filelist = folder.listFiles();

		for (int i = 0; i < filelist.length; i++) {
			if (filelist[i].isDirectory()) {
				this.checkFileTimestamp(filelist[i]);
			} else {
				if (deleteExecutableFile(filelist[i]))
					continue;

				long size = filelist[i].length() / 1024 / 1024;
				if (filelist[i].lastModified() < this.time && size > 100) {
					archiveAndMoveFile(filelist[i]);
				}
			}
		}
	}

	private boolean deleteExecutableFile(File file) {
		String filename = file.getName();
		if (filename.endsWith(".bat") || filename.endsWith(".sh") || filename.endsWith(".exe")) {
			this.countDeletedFiles++;
			System.out.println("Deleted file: " + file.getName());

			return file.delete();
		}
		return false;
	}

	private void printReport() {
		long securedFolderSize = this.getFileSize(new File(this.secured)) / 1024 / 1024;

		System.out.println(
				"Time: " + System.currentTimeMillis() + "Folder size: " + securedFolderSize + " MB, Total archived files: "
						+ this.countArchivedFiles + ", Total deleted files: " + this.countDeletedFiles);
	}

	@Override
	public void run() {
		this.countArchivedFiles = 0;
		this.countDeletedFiles = 0;
		this.currentTime = System.currentTimeMillis();
		this.time = this.currentTime - TIMER;

		long securedFolderSize = this.getFileSize(new File(this.secured)) / 1024 / 1024;

		try {
			if (securedFolderSize > 100) {
				this.checkFileTimestamp(new File(this.secured));
			}
			this.printReport();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TIMER = 5 * 60 * 1000;
		new Timer().schedule(new FolderHealthMonitor("D:/logs"), 0, TIMER);
	}

}
