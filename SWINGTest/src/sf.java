import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;


public class sf {

	public static void main(String[] args) throws IOException {

		String name="C:\\Users\\Admin\\Documents\\Statistics";
		ArrayList<File> allFiles = listFilesWithSubFolders(new File(name));
		//все файлы в папке и подпапках
		System.out.println(allFiles.toString());
		File[] files = allFiles.toArray(new File[allFiles.size()]);
		
		/*
		//путь до папки с файлами
		File sourceFolder = new File("c:\\!edit\\3333\\2014_2\\M399\\");
		File[] files = sourceFolder.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathName) {
               return pathName.toString().matches(".+\\.(dat|dbt)$");
            }
        });*/

		Arrays.sort(files);

		//массивы files1 - пары, files2 - без пары
		ArrayList<File> files1 = new ArrayList<File>();
		ArrayList<File> files2 = new ArrayList<File>();
		String oldFileName="";

		for(int i=0;i<files.length;i++){
			if(oldFileName.equals(files[i].getName().split("\\.")[0])){
				files1.add(files[i-1]);
				files1.add(files[i]);
				oldFileName="";
				continue;
			}


			if(oldFileName.length()>0){
				files2.add(files[i-1]);
			}

			//запомним последний
			oldFileName=files[i].getName().split("\\.")[0];

		}

		//выведем парные файлы
		System.out.println("Найдено парных: "+files1.size()+" -- "+files1.toString());

		//выведем одинарные
		System.out.println("Найдено без пары: "+files2.size()+" -- "+files2.toString());

		//проверим на пустоту и наличие тега [END] 
		//files3 - один или оба пустые, files4 - один или оба не содержит тег [END]
		//а в files5 - останутся только валидные
		ArrayList<File> files3 = new ArrayList<File>();
		ArrayList<File> files4 = new ArrayList<File>();
		ArrayList<File> files5 = new ArrayList<File>();

		//проверка парами
		for(int i=0; i<files1.size(); i+=2){
			if(files1.get(i).length()<1 || files1.get(i+1).length()<1){
				System.out.println("Найден пустой файл в паре: "+files1.get(i).getName()+", "+files1.get(i+1).getName());
				files3.add(files1.get(i));
				files3.add(files1.get(i+1));
				continue;
			}

			if(!containsEND(files1.get(i))||!containsEND(files1.get(i+1))){
				System.out.println("Найден файл в паре без тега [END]: "+files1.get(i).getName()+", "+files1.get(i+1).getName());
				files4.add(files1.get(i));
				files4.add(files1.get(i+1));
				continue;
			}

			files5.add(files1.get(i));
			files5.add(files1.get(i+1));
		}

		//полностью валидные
		System.out.println("Полность валидные пары: "+files5.toString());
	}

	public static boolean containsEND(File f) throws IOException{
		final int length = (int) f.length();
		char[] cBuf = new char[length];
		InputStreamReader isr = new InputStreamReader(new FileInputStream(f),"CP1251");
		final int read = isr.read(cBuf);
		String s = new String(cBuf, 0, read);
		isr.close();
		if(s.indexOf("[END]")!=-1)
			return true;
		else
			return false;
	}

	public static ArrayList<File> listFilesWithSubFolders(File dir) {
		ArrayList<File> files = new ArrayList<File>();
		for (File file : dir.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathName) {
				if(pathName.isDirectory())
					return true;
				else
					return pathName.toString().matches(".+\\.(dat|dbt)$");
			}
		})) {
			if (file.isDirectory())
				files.addAll(listFilesWithSubFolders(file));
			else
				files.add(file);
		}
		return files;
	}

}