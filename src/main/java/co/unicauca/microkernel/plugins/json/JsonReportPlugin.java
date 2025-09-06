/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package co.unicauca.microkernel.plugins.json;

import co.unicauca.microkernel.common.entities.Project;
import co.unicauca.microkernel.common.interfaces.IReportPlugin;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author User
 */
public class JsonReportPlugin implements  IReportPlugin  {

 

    @Override
    public String generateReport(List<Project> data) {
        StringBuilder json = new StringBuilder();
        json.append("[\n");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (int i = 0; i < data.size(); i++) {
            Project p = data.get(i);

            // Obtener estudiantes (máximo 2)
            String estudiante1 = p.getEstudiante().size() > 0 ? p.getEstudiante().get(0) : null;
            String estudiante2 = p.getEstudiante().size() > 1 ? p.getEstudiante().get(1) : null;

            json.append("  {\n");
            json.append("    \"id\": \"").append(p.getID()).append("\",\n");
            json.append("    \"nombre\": \"").append(p.getNomProyecto()).append("\",\n");
            json.append("    \"fechaFormatoA\": \"").append(p.getFechaAprobacion().format(formatter)).append("\",\n");

            json.append("    \"estudiante1\": ").append(estudiante1 != null ? "\"" + estudiante1 + "\"" : "null").append(",\n");
            json.append("    \"estudiante2\": ").append(estudiante2 != null ? "\"" + estudiante2 + "\"" : "null").append(",\n");

            json.append("    \"profesor\": \"").append(p.getProfesor()).append("\",\n");
            json.append("    \"tipo\": \"").append(p.getTipo()).append("\",\n");
            json.append("    \"programa\": \"").append(p.getPrograma()).append("\"\n");
            json.append("  }");

            if (i < data.size() - 1) {
                json.append(",");
            }
            json.append("\n");
        }

        json.append("]");
        return json.toString();
    }
}

   

