import javax.swing.*;

import java.io.File;
public class MyFrame extends JDialog {
	MyFrame()
	{
		//add(Component c);
		setBounds(0,0,500,500);
		JFileChooser dialog = new JFileChooser();

		dialog.setFileFilter(new myFileFilter(".dbt","dat"));
		dialog.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		dialog.setMultiSelectionEnabled(true);
		//dialog.setCurrentDirectory("H:\\hlam\\Statistics");
		dialog.showOpenDialog(this);
		File[] file = dialog.getSelectedFiles();
	}

	public static class myFileFilter extends javax.swing.filechooser.FileFilter {
		String ext, description;
		public String getDescription() {
			return description;
		}

		myFileFilter(String ext, String description) {
			this.ext = ext;
		}
		public boolean accept(File f) {
			if(f != null) {
				if(f.isDirectory()) {
					return true;


                }
				return f.toString().endsWith(ext);
			}
			return false;

		}
	}

}
