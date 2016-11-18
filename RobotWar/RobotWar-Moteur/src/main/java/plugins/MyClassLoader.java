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

public class MyClassLoader extends SecureClassLoader {
	private ArrayList<File> path = new ArrayList<File>();

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] b = loadClassData(name);
		return super.defineClass(name, b, 0, b.length);
	}

	private byte[] loadClassData(String name) throws ClassNotFoundException {

		File fichier = null;

		name = name.replace(".", "/") + ".class";

		// Récupération du File chemin
		for (File file : path) {

			// Si c'est un répertoire
			if (file.isDirectory()) {
				String chemin = file.getAbsolutePath() + "/" + name;

				// Test du chemin
				fichier = new File(chemin);

				// Si le chemin existe
				if (fichier.exists()) {
					break;
				}
			} else
			// Si c'est un .jar ou un .zip
			if (file.getAbsolutePath().endsWith(".jar") || file.getAbsolutePath().endsWith(".zip")) {

				ZipInputStream zip;
				try {
					zip = new ZipInputStream(new FileInputStream(file));

					ZipEntry entry;
					// Parcours de tous les éléments du jar
					while ((entry = zip.getNextEntry()) != null) {
						// Si on trouve le bon fichier
						if (entry.toString().equals(name)) {

							// Permet d'initialiser un tab sans lui donner la
							// taille
							ByteArrayOutputStream streamBuilder = new ByteArrayOutputStream();
							int bytesRead;

							// Buffer temporaire
							byte[] tempBuffer = new byte[8192 * 2];
							try {
								// On lit tant qu'il reste des bytes à lire
								while ((bytesRead = zip.read(tempBuffer)) != -1) {
									streamBuilder.write(tempBuffer, 0, bytesRead);
								}
							} catch (IOException e) {
								e.printStackTrace();
							}
							// On retourne ce qu'on a lu
							return streamBuilder.toByteArray();
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		// Si le chemin n'existe pas
		if (!fichier.exists() || fichier == null) {
			throw new ClassNotFoundException();
		}

		System.out.println("Le fichier existe : " + fichier.getAbsolutePath());

		byte[] tab = null;
		try {
			// On lit le contenu du .class
			tab = Files.readAllBytes(fichier.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return tab;
	}
	
}