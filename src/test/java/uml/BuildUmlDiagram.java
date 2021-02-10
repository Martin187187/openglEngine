package uml;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.elnarion.util.plantuml.generator.PlantUMLClassDiagramGenerator;

public class BuildUmlDiagram {

	public static void main(String[] args) {
		BuildUmlDiagram d = new BuildUmlDiagram();
		List<String> scanpackages = new ArrayList<>();
		scanpackages.add("engine");
		List<String> hideClasses = new ArrayList<>();
		PlantUMLClassDiagramGenerator generator =
			new PlantUMLClassDiagramGenerator(d.getClass().getClassLoader(),
			scanpackages,null, hideClasses, false, false);
		try {
			String result = generator.generateDiagramText();
			System.out.println(result);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
