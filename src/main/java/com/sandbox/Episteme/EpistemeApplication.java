package com.sandbox.Episteme;

import com.sandbox.Episteme.business.DimensionService;
import com.sandbox.Episteme.business.DomainService;
import com.sandbox.Episteme.business.ResourceService;
import com.sandbox.Episteme.model.Dimension;
import com.sandbox.Episteme.model.DimensionCategory;
import com.sandbox.Episteme.model.Domain;
import com.sandbox.Episteme.model.DomainCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@SpringBootApplication
public class EpistemeApplication implements CommandLineRunner {
  //  private final String EPISTEM_IMPORT_FILE = "EpistemeBase.json";

    @Autowired
    private DomainService domainService;

    @Autowired
    private DimensionService dimensionService;

    @Autowired
    private ResourceService resourceService;

    public static void main(String[] args) {

        SpringApplication.run(EpistemeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        createDimensions();
        createDomains();
        connectDimensionsToDomains();
        updateDimensions();
        updateDomains();
//        System.out.println("Persisted Packages = " + tourPackageService.total());
//        createToursFromFile(TOUR_IMPORT_FILE);
//        System.out.println("Persisted Tours = " + tourService.total());
       
        /********* CHALLENGES **********/
        // System.out.println("\n\nEasy Tours");
        // tourService.lookupByDifficulty(Difficulty.Easy).forEach(System.out::println);

        // System.out.println("\n\nBackpack Cali Tours");
        // tourService.lookupByPackage("BC").forEach(System.out::println);
    }

    /**
     * Iterate through all of the tour packages, print the tour package name and
     * for each tour package lookup all tours and print the name and
     * description of the tour.
     * 
     */
    private void printToursChallenge() {

    }

    private enum BaseDimensions {
        BACK_DEV("Back End Developer"),
        DEV_OPS("DevOps Engineer"),
        FRONT_DEV("Front End Developer"),
        FS_DEV("Full Stack Developer"),
        INTEG_ARCH("Integration Architect"),
        M2_ARCH("Mainframe Modernization Architect"),
        M2_ENG("Mainframe Modernization Engineer"),
        SOL_ARCH("Solutions Architect"),
        SW_ARCH("Software Architect");

        private String text;
        private BaseDimensions(String txt) {
            this.text = txt;
        }

        @Override
        public String toString() {
            return  text;
        }
        private static Stream<BaseDimensions> stream() {
            return Stream.of(BaseDimensions.values());
        }

    }

    private Map<BaseDimensions, Dimension> baseDimensions = new HashMap<>();

    private Dimension getBaseDimension(BaseDimensions bd) {
        return baseDimensions.get(bd);
    }
    private Dimension createBaseDimension(BaseDimensions bd, DimensionCategory categ, String desc) {
        Dimension dim=getBaseDimension(bd);
        if (dim!=null)
            return dim;
        baseDimensions.put(bd,dimensionService.createDimension(bd.toString(), categ, desc));
        return baseDimensions.get(bd);
    }


    /**
     * Initialize all the known tour packages
     */
    private void createDimensions() {
        createBaseDimension(BaseDimensions.SW_ARCH, DimensionCategory.Role, null);
        createBaseDimension(BaseDimensions.SOL_ARCH, DimensionCategory.Role, null);
        createBaseDimension(BaseDimensions.INTEG_ARCH, DimensionCategory.Role, null);
        createBaseDimension(BaseDimensions.FS_DEV, DimensionCategory.Role, null);
        createBaseDimension(BaseDimensions.BACK_DEV, DimensionCategory.Role, null);
        createBaseDimension(BaseDimensions.FRONT_DEV, DimensionCategory.Role, null);
        createBaseDimension(BaseDimensions.DEV_OPS, DimensionCategory.Role, null);
        createBaseDimension(BaseDimensions.M2_ARCH, DimensionCategory.Role, null);
        createBaseDimension(BaseDimensions.M2_ENG, DimensionCategory.Role, null);
    }

    private enum BaseDomains {
        DOT_NET(".NET"),
        JAVA("Java"),
        SYS_DESIGN("System Design"),
        PROGRAMMING("Development"),
        NETWORK("Network"),
        SECURITY("Security"),
        AI("Artificial Inteligence"),
        DATA_STORAGE("Data Storage"),
        SOFT_SKILLS("Soft Skills"),
        UI("Front End Developer"),
        DEV_OPS("DevOps");

        private String text;
        private BaseDomains(String txt) {
            this.text = txt;
        }
        @Override
        public String toString() {
            return  text;
        }
        private static Stream<BaseDomains> stream() {
            return Stream.of(BaseDomains.values());
        }
    }
    private Map<BaseDomains, Domain> baseDomains = new HashMap<>();

    private Domain getBaseDomain(BaseDomains bd) {
        return baseDomains.get(bd);
    }
    private Domain createBaseDomain(BaseDomains bd, DomainCategory categ, String desc) {
        Domain dom=getBaseDomain(bd);
        if (dom!=null)
            return dom;
        baseDomains.put(bd,domainService.createDomain(bd.toString(), categ, desc));
        return baseDomains.get(bd);
    }

    private void createDomains() {
        createBaseDomain(BaseDomains.DOT_NET, DomainCategory.Platform, null);
        createBaseDomain(BaseDomains.JAVA, DomainCategory.Platform,null);
        createBaseDomain(BaseDomains.SYS_DESIGN,  DomainCategory.Skill,null);
        createBaseDomain(BaseDomains.PROGRAMMING,DomainCategory.Skill, null);
        createBaseDomain(BaseDomains.NETWORK,DomainCategory.Skill, null);
        createBaseDomain(BaseDomains.SECURITY, DomainCategory.Skill,null);
        createBaseDomain(BaseDomains.AI, DomainCategory.Skill,null);
        createBaseDomain(BaseDomains.DATA_STORAGE, DomainCategory.Library,null);
        createBaseDomain(BaseDomains.SOFT_SKILLS, DomainCategory.Skill,null);
        createBaseDomain(BaseDomains.UI,DomainCategory.Guideline, null);
        createBaseDomain(BaseDomains.DEV_OPS, DomainCategory.Technology,null);
    }

    private void connectDimensionsToDomains() {
        connectDimDom(BaseDimensions.BACK_DEV, BaseDomains.DATA_STORAGE);
        connectDimDom(BaseDimensions.BACK_DEV, BaseDomains.PROGRAMMING);
        connectDimDom(BaseDimensions.BACK_DEV, BaseDomains.DEV_OPS);
        connectDimDom(BaseDimensions.BACK_DEV,  BaseDomains.NETWORK);

        connectDimDom(BaseDimensions.DEV_OPS, BaseDomains.DEV_OPS);

        connectDimDom(BaseDimensions.FRONT_DEV, BaseDomains.DEV_OPS);
        connectDimDom(BaseDimensions.FRONT_DEV, BaseDomains.UI);
        connectDimDom(BaseDimensions.FRONT_DEV, BaseDomains.PROGRAMMING);
        connectDimDom(BaseDimensions.FRONT_DEV,  BaseDomains.NETWORK);
        connectDimDom(BaseDimensions.FRONT_DEV, BaseDomains.SECURITY);

    }
    private void connectDimDom(BaseDimensions bdim, BaseDomains bdom ) {
        Dimension dim = getBaseDimension(bdim);
        Domain dom = getBaseDomain(bdom);
        dom.setDimension(dim);
    }
    private void updateDimensions() {
        BaseDimensions.stream()
                .forEach(dim -> dimensionService.updateDimension(getBaseDimension(dim)));
    }
    private void updateDomains() {
        BaseDomains.stream()
                .forEach(dom -> domainService.updateDomain(getBaseDomain(dom)));
    }
    /**
     * Create tour entities from an external file
     */
//    private void createToursFromFile(String fileToImport) throws IOException {
//        TourFromFile.read(fileToImport).forEach(t -> tourService.createTour(
//                t.packageName(),
//                t.title(),
//                t.description(),
//                t.blurb(),
//                t.price(),
//                t.length(),
//                t.bullets(),
//                t.keywords(),
//                Difficulty.valueOf(t.difficulty()),
//                Region.findByLabel(t.region())));
//    }

    /*
     * Helper to import ExploreCali.json
     */
//    record TourFromFile(String packageName, String title, String description,
//            String blurb, Integer price, String length, String bullets,
//            String keywords, String difficulty, String region) {
//        static List<TourFromFile> read(String fileToImport) throws IOException {
//            return new ObjectMapper().readValue(new File(fileToImport),
//                    new TypeReference<List<TourFromFile>>() {
//                    });
//        }
//    }
}
