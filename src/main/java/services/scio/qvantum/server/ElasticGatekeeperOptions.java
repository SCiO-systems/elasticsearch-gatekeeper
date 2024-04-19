package services.scio.qvantum.server;

import org.apache.commons.cli.Options;

public class ElasticGatekeeperOptions {

    public ElasticGatekeeperOptions() {
    }

    public Options createOptions(){

        Options options = new Options();

        options.addOption("ES", true, "Elasticsearch Configuration");
        options.addOption("IP", true, "Api IP");
        options.addOption("Port", true, "Port");
        options.addOption("file_path", true, "file path for creating and appending data for non existing mime types");

        return options;
    }
}
