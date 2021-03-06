package com.pl.tagc.tagcwebapp;

import controller.Controller;
import genomefeature.GenomeSearchResult;
import genomefeature.GraphSearcher.SearchType;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * The Class BackEndAdapter.
 */
public final class BackEndAdapter implements BackEndInterface {

    private static BackEndAdapter ba = null;
    private static Controller controller = null;
    private static HashMap<String, SearchType> searchTypeMap;

    private BackEndAdapter() {
        searchTypeMap = new HashMap<String, SearchType>();
        searchTypeMap.put("GenomicFeatureSearch", SearchType.GenomicFeatureSearch);
        searchTypeMap.put("MetaDataSearch", SearchType.MetaDataSearch);
        searchTypeMap.put("MutationSearch", SearchType.MutationSearch);
        searchTypeMap.put("FullSearch", SearchType.FullSearch);
    }

    /**
     * Get the singleton adapter instance.
     *
     * @return The adapter instance.
     */
    public static BackEndAdapter getInstance() {
        return ba;
    }

    /**
     * Creates the adapter instance.
     *
     * @param controller the controller
     */
    public static void createInstance(Controller controller) {
        if (ba == null) {
            ba = new BackEndAdapter();
            BackEndAdapter.controller = controller;
        }
    }

    @Override
    public Response getRibbonNodes(int minX, int maxX, int zoomLevel, boolean isMiniMap) {
        NodeListObject nodeList =
                new NodeListObject(new CopyOnWriteArrayList<>(
                        controller.getRibbonNodes(minX, maxX, zoomLevel, isMiniMap)));
        return Response.ok() //200
                .entity(nodeList)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build();
    }

    @Override
    public ArrayListObject setActiveGenomes(List<String> ids) {
        return new ArrayListObject(controller.setActiveGenomes((ArrayList<String>) ids));
    }

    @Override
    public Response loadPhylogeneticTree(int treeId) {
        PhylogeneticTreeObject result =
                new PhylogeneticTreeObject(controller.
                        loadPhylogeneticTree(treeId).getRoot());
        return Response.ok() //200
                .entity(result)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build();
    }

    @Override
    public SearchResultObject search(String searchString, String searchTypeStr) {
        SearchType searchType = searchTypeMap.get(searchTypeStr);
        if (searchType == null) {
            throw new IllegalArgumentException("Unknown search type");
        }
        GenomeSearchResult gSearchRresult = controller.search(searchString, searchType);
        SearchResultObject resultObject = new SearchResultObject();
        resultObject.setgFeatureSearchMatches(gSearchRresult.getgFeatureSearchMatches());
        return resultObject;
    }

    /**
     * Get the datamap and put it in a JAXB readable object.
     * @return a JAXB readable MetaDataMap.
     */
	public MetaDataObject getMetaDataMap() {
		return new MetaDataObject(controller.getMetaDataController().getMetaDataMap());
	}

    /**
     * Get the colors for the given metadata and put it in a JAXB readable object.
     * @param metaData Sting of metadata to get the color for.
     * @return a JAXB readable ColorMap.
     */
	public MetaDataColorObject getAllGenomeColors(String metaData) {
		return new MetaDataColorObject(controller.getMetaDataController().
				getAllGenomeColors(metaData));
	}

    /**
     * Used for setting color blind mode in the metadata controller.
     * @param colorBlindMode whether or not color blind mode is enabled.
     */
    public void setColorBlindMode(Boolean colorBlindMode) {
        controller.getMetaDataController().setColorBlindEnabled(colorBlindMode);
    }

    /**
     * Used for getting the current colorBlindMode in case of a page refresh.
     * @return colorBlindMode whether or not color blind mode is enabled.
     */
    public BooleanObject getColorBlindMode() {
        return new BooleanObject(controller.getMetaDataController().getColorBlindMode());
    }
}
