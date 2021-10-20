import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        createXml();
        readXml();
    }
    public static void createXml() {
        ArrayList<Persona> employees = createEmployees();  //Guardamos el arraylist de la funcion llamada
        try
        {
            //Utilizamos estas clases para crear un documento tipo xml
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.newDocument();

            //Ponemos el elemento raiz
            Element root = document.createElement("company");
            document.appendChild(root);

            //guardamos en el documentos los empleados del arraylist con un foreach
            for(Persona employee : employees)
            {
                Element trabajador = document.createElement("employee");
                root.appendChild(trabajador);

                Attr attrId = document.createAttribute("id");
                attrId.setValue(String.valueOf(employee.getId()));
                trabajador.setAttributeNode(attrId);

                Element nombre = document.createElement("name");
                nombre.appendChild(document.createTextNode(employee.getNombre()));
                trabajador.appendChild(nombre);

                Element edad = document.createElement("edad");
                edad.appendChild(document.createTextNode(String.valueOf(employee.getEdad())));
                trabajador.appendChild(edad);
            }

            //Transformamos el archivo en un xml
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File("pruebaTrabajadores.xml"));
            transformer.transform(domSource, streamResult);
            System.out.println("Archivo XML creado");

        } catch (ParserConfigurationException | TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
    public static void readXml() {
        try{
            //Creamos un objeto file para leer el xml ya creado
            File f = new File("pruebaTrabajadores.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(f);
            doc.getDocumentElement().normalize();
            //Sacamos el elemto raiz por pantalla y sacamos los elementos debajo de este
            System.out.println("Elemento raiz: " + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("employee");

            //Hacemos un for para sacar todos los empleados
            for(int i = 0; i < nodeList.getLength(); i++)
            {
                Node node = nodeList.item(i);
                System.out.println("Node name: " + node.getNodeName());
                //Sacamos los elementos del nodo "employees"
                if(node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element eElement = (Element) node;
                    System.out.println("Nombre: " + eElement.getElementsByTagName("name").item(0).getTextContent());
                    System.out.println("Edad: " + eElement.getElementsByTagName("edad").item(0).getTextContent());
                    System.out.println("Id: " + eElement.getAttribute("id"));
                }
            }



        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<Persona> createEmployees() {
        ArrayList<Persona> employees = new ArrayList<Persona>();
        Scanner teclado = new Scanner(System.in);
        int cantidadEmployees;
        int edad ,id;
        String nombre;
        Persona p;
        System.out.print("Dime cuantas personas quieres guardar: ");
        cantidadEmployees = teclado.nextInt();
        teclado.nextLine();
        for(int i = 0; i < cantidadEmployees; i++)
        {
            System.out.println("Persona "  + (i+1));
            System.out.print("Dime el nombre: ");
            nombre = teclado.nextLine();
            System.out.print("Dime la edad: ");
            edad = teclado.nextInt();
            System.out.print("Dime el id: ");
            id = teclado.nextInt();
            teclado.nextLine();

            p  = new Persona(id,edad,nombre);
            employees.add(p);
        }
        return employees;
    }
}