package net.d4rkfly3r.engine.world;

import net.d4rkfly3r.engine.Engine;
import net.d4rkfly3r.engine.RenderUtils;
import net.d4rkfly3r.engine.images.Texture;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.annotation.Nonnull;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class World {


    private static void parseFromXML(String name) {
        try {

            File fXmlFile = new File(name);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

            NodeList staticParts = doc.getDocumentElement().getElementsByTagName("static");

            if (staticParts.getLength() > 0) {
                for (int i = 0; i < staticParts.getLength(); i++) {
                    if (staticParts.item(i).getNodeType() == Node.ELEMENT_NODE) {
                        NodeList imageList = ((Element) staticParts.item(i)).getElementsByTagName("image");
                        for (int j = 0; j < imageList.getLength(); j++) {
                            if (imageList.item(j).getNodeType() == Node.ELEMENT_NODE) {
                                Element imageElem = (Element) imageList.item(j);

                                if (imageElem.getAttribute("type").equalsIgnoreCase("file")) {
                                    saveImageToRender(imageElem);
                                } else {
                                    saveImageToRenderOt(imageElem);

                                }
//                                System.out.println("Type: " + imageElem.getAttribute("type"));
//                                System.out.println("Link: " + imageElem.getElementsByTagName("link").item(0).getTextContent());
//                                System.out.println("X: " + imageElem.getElementsByTagName("x").item(0).getTextContent());
//                                System.out.println("Y: " + imageElem.getElementsByTagName("y").item(0).getTextContent());
//                                NodeList widthL = imageElem.getElementsByTagName("width");
//                                NodeList heightL = imageElem.getElementsByTagName("height");
//                                System.out.println("Width: " + (widthL.getLength() > 0 ? widthL.item(0).getTextContent() : ""));
//                                System.out.println("Height: " + (heightL.getLength() > 0 ? heightL.item(0).getTextContent() : ""));
//                                System.out.println("\n\n");
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void saveImageToRender(@Nonnull Element imageElem) {
        try {
            Texture texture = new Texture(new File(imageElem.getElementsByTagName("link").item(0).getTextContent()).toURI());
            int x = Integer.parseInt(imageElem.getElementsByTagName("x").item(0).getTextContent());
            int y = Integer.parseInt(imageElem.getElementsByTagName("y").item(0).getTextContent());
            NodeList widthL = imageElem.getElementsByTagName("width");
            NodeList heightL = imageElem.getElementsByTagName("height");
            int width = widthL.getLength() > 0 ? Integer.parseInt(widthL.item(0).getTextContent()) : texture.getWidth();
            int height = heightL.getLength() > 0 ? Integer.parseInt(heightL.item(0).getTextContent()) : texture.getHeight();

            int repeatX = 1, repeatY = 1;

            NodeList repeat = imageElem.getElementsByTagName("repeat");
            if (repeat.getLength() > 0) {
                final Element item = (Element) repeat.item(0);
                final NodeList xNodeList = item.getElementsByTagName("x");
                final NodeList yNodeList = item.getElementsByTagName("y");
                repeatX = xNodeList.getLength() > 0 ? Integer.parseInt(xNodeList.item(0).getTextContent()) : 1;
                repeatY = yNodeList.getLength() > 0 ? Integer.parseInt(yNodeList.item(0).getTextContent()) : 1;
            }

            final int finalRepeatX = repeatX;
            final int finalRepeatY = repeatY;

            Engine.getInstance().addRender(1, () -> {
//                RenderUtils.renderSprite(texture, x, y, width, height);
                for (int rX = 0; rX < finalRepeatX; rX++) {
                    for (int rY = 0; rY < finalRepeatY; rY++) {
                        RenderUtils.renderSprite(texture, x + width * rX, y + height * rY, width, height);
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveImageToRenderOt(@Nonnull Element imageElem) {
        try {
            Texture texture = new Texture(new URL(imageElem.getElementsByTagName("link").item(0).getTextContent()).toURI());
            int x = Integer.parseInt(imageElem.getElementsByTagName("x").item(0).getTextContent());
            int y = Integer.parseInt(imageElem.getElementsByTagName("y").item(0).getTextContent());
            NodeList widthL = imageElem.getElementsByTagName("width");
            NodeList heightL = imageElem.getElementsByTagName("height");
            int width = widthL.getLength() > 0 ? Integer.parseInt(widthL.item(0).getTextContent()) : texture.getWidth();
            int height = heightL.getLength() > 0 ? Integer.parseInt(heightL.item(0).getTextContent()) : texture.getHeight();

            Engine.getInstance().addRender(1, () -> {
                RenderUtils.renderSprite(texture, x, y, width, height);
            });

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void renderWorld() {
        parseFromXML("LevelSpec.xml");
    }

}
