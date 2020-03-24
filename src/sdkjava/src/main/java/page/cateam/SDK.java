package page.cateam;

import com.oracle.bmc.ConfigFileReader;
import com.oracle.bmc.Region;
import com.oracle.bmc.auth.AuthenticationDetailsProvider;
import com.oracle.bmc.auth.ConfigFileAuthenticationDetailsProvider;
import com.oracle.bmc.database.DatabaseClient;
import com.oracle.bmc.database.model.AutonomousDatabaseSummary;
import com.oracle.bmc.database.requests.ListAutonomousDatabasesRequest;
import com.oracle.bmc.database.responses.ListAutonomousDatabasesResponse;
import com.oracle.bmc.identity.IdentityClient;
import com.oracle.bmc.identity.model.AvailabilityDomain;
import com.oracle.bmc.identity.requests.ListAvailabilityDomainsRequest;
import com.oracle.bmc.identity.responses.ListAvailabilityDomainsResponse;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class SDK {

	private Config config;

	public SDK(Config config) {
		this.config = config;
	}

	public void printATPs() throws IOException {
		final AuthenticationDetailsProvider provider = getProvider();
		DatabaseClient client = new DatabaseClient(provider);
		String compartmentId = config.getProperties().getProperty("COMPARTMENT_OCID");
		ListAutonomousDatabasesRequest request = ListAutonomousDatabasesRequest.builder()
				.compartmentId(compartmentId)
				.build();
		ListAutonomousDatabasesResponse response = client.listAutonomousDatabases(request);
		String dbNames = response.getItems()
				.stream()
				.map(AutonomousDatabaseSummary::getDbName)
				.collect(Collectors.joining(", "));
		System.out.println(String.format("ATPs: %s", dbNames));
	}

	public void printAvailabilityDomains() throws Exception {
		String compartmentId = config.getProperties().getProperty("COMPARTMENT_OCID");
		String regionId = config.getProperties().getProperty("REGION_ID");
		final AuthenticationDetailsProvider provider = getProvider();
		IdentityClient identityClient =
				IdentityClient.builder().build(provider);
		identityClient.setRegion(Region.fromRegionId(regionId));
		ListAvailabilityDomainsResponse listAvailabilityDomainsResponse =
				identityClient.listAvailabilityDomains(
						ListAvailabilityDomainsRequest.builder()
								.compartmentId(compartmentId)
								.build());
		List<AvailabilityDomain> availabilityDomains = listAvailabilityDomainsResponse.getItems();

		String availabilityDomainNames = availabilityDomains
				.stream()
				.map(AvailabilityDomain::getName)
				.collect(Collectors.joining(", "));
		System.out.println(String.format("%s ADs: %s", regionId, availabilityDomainNames));
	}

	private AuthenticationDetailsProvider getProvider() throws IOException {
		String configLocation = config.getProperties().getProperty("CONFIG_LOCATION");
		String configProfile = config.getProperties().getProperty("CONFIG_PROFILE");
		ConfigFileReader.ConfigFile configFile =
				ConfigFileReader.parse(configLocation, configProfile);
		AuthenticationDetailsProvider provider =
				new ConfigFileAuthenticationDetailsProvider(configFile);
		return provider;
	}

}
