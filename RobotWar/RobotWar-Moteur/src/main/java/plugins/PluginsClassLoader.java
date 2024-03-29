package plugins;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.security.SecureClassLoader;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class PluginsClassLoader extends SecureClassLoader {
	private String cheminDuPlugin;

	public PluginsClassLoader(String chemin) {
		cheminDuPlugin = chemin;
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] b = loadClassData(name);
		System.out.println(name);
		return super.defineClass(name, b, 0, b.length);
	}

	private byte[] loadClassData(String name) throws ClassNotFoundException {

		File fichier = new File(cheminDuPlugin);

		try {
			return Files.readAllBytes(fichier.toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//
		// // Si c'est un .jar ou un .zip
		// if (file.getAbsolutePath().endsWith(".jar") ||
		// file.getAbsolutePath().endsWith(".zip")) {
		//
		// ZipInputStream zip;
		// try {
		// zip = new ZipInputStream(new FileInputStream(file));
		//
		// ZipEntry entry;
		// // Parcours de tous les �l�ments du jar
		// while ((entry = zip.getNextEntry()) != null) {
		// // Si on trouve le bon fichier
		// if (entry.toString().equals(name)) {
		//
		// // Permet d'initialiser un tab sans lui donner la
		// // taille
		// ByteArrayOutputStream streamBuilder = new ByteArrayOutputStream();
		// int bytesRead;
		//
		// // Buffer temporaire
		// byte[] tempBuffer = new byte[8192 * 2];
		// try {
		// // On lit tant qu'il reste des bytes � lire
		// while ((bytesRead = zip.read(tempBuffer)) != -1) {
		// streamBuilder.write(tempBuffer, 0, bytesRead);
		// }
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// // On retourne ce qu'on a lu
		// return streamBuilder.toByteArray();
		// }
		// }
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// }
		//
		// // Si le chemin n'existe pas
		// if (!fichier.exists() || fichier == null) {
		// throw new ClassNotFoundException();
		// }
		//
		// System.out.println("Le fichier existe : " +
		// fichier.getAbsolutePath());
		//
		// byte[] tab = null;
		// try {
		// // On lit le contenu du .class
		// tab = Files.readAllBytes(fichier.toPath());
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		//
		// return tab;
		return null;
	}

}