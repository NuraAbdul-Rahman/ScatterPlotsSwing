import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
/*
 * @author: Nura Abdul-Rahman -2418644a
 */
public class FileChooser {

	public File chooseFile() {
		File selectedFile = null;
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			selectedFile = jfc.getSelectedFile();
		}
		
		return selectedFile;

	}

}