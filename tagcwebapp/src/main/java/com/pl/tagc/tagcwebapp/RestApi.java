package com.pl.tagc.tagcwebapp;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.List;

//The Java class will be hosted at the URI path "/api"

/**
 * The Class RestApi. The Java class will be hosted at the URI path "/api". The
 * annotation @GET in the class means the Java method will process HTTP GET
 * request. The annotation @Produces("application/json") means the method will
 * produce content identified by the MIME Media type "application/json".
 *
 * @author Kasper Grabarz
 */

@Path("/api")
public class RestApi {

    /**
     * Requests nodes that have their coordinates within the bounding box
     * defined by the parameters.
     *
     * @param xleft     the left bound of the bounding box
     * @param xright    the right bound of the bounding box
     * @param zoom      the zoom level that decides which nodes get filtered
     * @param isMiniMap determine wether this is a minimap call or not.
     * @return the node list object
     */
    @GET
    @Path("/getnodes")
    @Produces("application/json")
    public Response requestNodes(@DefaultValue("0") @QueryParam("xleft") int xleft,
                                 @DefaultValue("100") @QueryParam("xright") int xright,
                                 @DefaultValue("1") @QueryParam("zoom") int zoom,
                                 @DefaultValue("false") @QueryParam("isMiniMap")
                                         boolean isMiniMap) {
        return BackEndAdapter.getInstance().getRibbonNodes(xleft, xright, zoom, isMiniMap);
    }

    /**
     * Uses the genome ids to set the genomes as active in the backend. Which means that
     * they will be used to generate the ribbongraph when getnodes is called.
     *
     * @param ids the genome ids
     * @return the list      List of unrecognized genomes.
     */
    @POST
    @Path("/setactivegenomes")
    @Produces("application/json")
    public Response setActiveGenomes(@FormParam("names[]") List<String> ids) {
        ArrayListObject result = BackEndAdapter.getInstance().setActiveGenomes(ids);
        return Response.ok() //200
                .entity(result)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build();
    }


    /**
     * Returns a Hashmap mapping genomes to color.
     * @return the list      List of unrecognized genomes.
     */
    @GET
    @Path("/getmetadatamap")
    @Produces("application/json")
    public Response getMetaDataMap() {
        return Response.ok()
                .entity(BackEndAdapter.getInstance().getMetaDataMap())
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build();
    }

    /**
     * Returns a Hashmap mapping genomes to color.
     * @param metaData The metadata to get the the colors for.
     * @return the List of unrecognized genomes.
     */
    @GET
    @Path("/getgenomecolors")
    @Produces("application/json")
    public Response getMetaGenomeColors(@DefaultValue("lineage")
                                        @QueryParam("metadata") String metaData) {
        return Response.ok()
                .entity(BackEndAdapter.getInstance().getAllGenomeColors(metaData))
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build();
    }

    /**
     * Set whether color blind mode is enabled
     * @param mode true if enabled
     * @return a response
     */
    @GET
    @Path("/setcolorblindmode")
    @Produces("application/json")
    public Response setColorBlindMode(@QueryParam("mode") Boolean mode) {
        BackEndAdapter.getInstance().setColorBlindMode(mode);
        return Response.ok() //200
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build();
    }

    /**
     * Get whether color blind mode is enabled
     * @return a response
     */
    @GET
    @Path("/getcolorblindmode")
    @Produces("application/json")
    public Response getColorBlindMode() {
        return Response.ok() //200
                .entity(BackEndAdapter.getInstance().getColorBlindMode())
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build();
    }

    /**
     * Request phylogenetic tree.
     *
     * @param treeId the tree id
     * @return the response
     */
    @GET
    @Path("/getphylogenetictree")
    @Produces("application/json")
    public Response requestPhylogeneticTree(@DefaultValue("1") @QueryParam("treeId") int treeId) {
        return BackEndAdapter.getInstance().loadPhylogeneticTree(treeId);

    }

    
    /**
     * Request phylogenetic tree.
     *
     * @param searchString the search string
     * @param searchType   the search type
     * @return the response
     */
    @GET
    @Path("/search")
    @Produces("application/json")
    public Response search(@QueryParam("searchString") String searchString,
                           @QueryParam("searchType") String searchType) {
        SearchResultObject result = BackEndAdapter.getInstance().search(searchString, searchType);
        return Response.ok() //200
                .entity(result)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build();
    }

}
