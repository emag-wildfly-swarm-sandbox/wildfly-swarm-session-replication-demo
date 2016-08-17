package wildflyswarm;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.wildfly.swarm.container.Container;
import org.wildfly.swarm.infinispan.InfinispanFraction;
import org.wildfly.swarm.undertow.WARArchive;
import wildflyswarm.sessionreplication.AccessCounterServlet;

public class App {

  public static void main(String[] args) throws Exception {
    Container container = new Container(args);

    WARArchive archive = ShrinkWrap.create(WARArchive.class);
    archive.addClass(AccessCounterServlet.class);
    archive.addAsWebInfResource(new ClassLoaderAsset("WEB-INF/web.xml", App.class.getClassLoader()), "web.xml");

    container.start();

    InfinispanFraction infinispan = (InfinispanFraction) container.fractions().stream()
      .filter(f -> f.simpleName().equals("Infinispan"))
      .findFirst()
      .get();

    infinispan.subresources()
      .cacheContainer("web")
      .jgroupsTransport(j -> j.channel("swarm-jgroups"));

    container.deploy(archive);
  }

}
