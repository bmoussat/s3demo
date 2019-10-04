package com.storage.s3demo.localstack;

import cloud.localstack.ServiceName;
import cloud.localstack.docker.LocalstackDocker;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EndpointRegistrar {

	@Getter
	@AllArgsConstructor
	private enum Service {

		S3(ServiceName.S3, "http://localhost:4572");

		private String name;
		private String defaultEndpoint;

	}

	public static String getS3Endpoint() {
		return getServiceEndpoint(Service.S3);
	}

	private static String getServiceEndpoint(final Service service) {
		try {
			return LocalstackDocker.INSTANCE.endpointForService(service.getName());
		} catch (final Exception e) {
			return service.getDefaultEndpoint();
		}
	}

}
