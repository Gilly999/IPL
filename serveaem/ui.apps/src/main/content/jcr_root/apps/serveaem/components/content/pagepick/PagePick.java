
package apps.serveaem.components.content.pagepick;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import java.util.*;

import java.text.SimpleDateFormat;
import javax.jcr.Node;
import javax.jcr.Session;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;
import javax.jcr.NodeIterator;
import com.adobe.cq.sightly.WCMUsePojo;
import com.day.cq.commons.jcr.JcrConstants;
import com.day.cq.wcm.api.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PagePick extends WCMUsePojo {

    private static final String TYPE_DEFAULT = "default";
    private static final String PN_TYPE = "displayAs";
    private String id;
    private String type;
    private Resource resource;
    private ResourceResolver resolver;
    public List<String> pageList;
	private static Logger logger = LoggerFactory.getLogger(PagePick.class);
    @Override
    public void activate() throws Exception {
        this.resource = getResource();

        this.resolver=getResourceResolver();
		ValueMap properties = getProperties();
        String path=properties.get("path", String.class);

        Date date = properties.get("date", Date.class);

         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

        String cDate = dateFormat.format(date);

        pageList = getPageList(path,cDate);

    }
    private List<String> getPageList(String path, String date){
		List<String> pages = new ArrayList<String>();
		Session session = resolver.adaptTo(Session.class);
        String assetQuery = "select * from [cq:Page] as a where [jcr:created] > cast('"+date+"' as date) and isdescendantnode(a, '"+path+"')";

        QueryManager queryManager;
        try {
        queryManager = session.getWorkspace().getQueryManager();
        Query query = queryManager.createQuery(assetQuery, Query.JCR_SQL2);
        QueryResult assets = query.execute();
        NodeIterator assetIterator = assets.getNodes();
            while (assetIterator.hasNext()) {

                Node pageNode = (Node) assetIterator.nextNode();
				pages.add(pageNode.getName());
        	}


        }catch(Exception e){}
        return pages;
    }

    public List<String> getPageName(){
        return pageList;
    }

}
