package wildflyswarm;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.wildfly.swarm.container.Container;
import org.wildfly.swarm.jgroups.JGroupsFraction;
import org.wildfly.swarm.undertow.WARArchive;
import wildflyswarm.sessionreplication.AccessCounterServlet;

public class App {

  public static void main(String[] args) throws Exception {
    Container container = new Container(args);

    WARArchive archive = ShrinkWrap.create(WARArchive.class);
    archive.addClass(AccessCounterServlet.class);
    archive.addAsWebInfResource(new ClassLoaderAsset("WEB-INF/web.xml", App.class.getClassLoader()), "web.xml");

    container.start().deploy(archive);
  }

}
