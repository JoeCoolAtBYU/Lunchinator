package barnett.joshua.lunchinator.swagger;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(
        prefix = "barnett.docs"
)
public class SwaggerProperties {
    private String apiVersion = "0.0.0";
    private String[] documentedEndpoints = new String[]{"/.*"};
    private String apiLongName = "No Version Name Specified";
    private String apiDescription = "No API Description Given";
    private String developerEmail = "developer@email.com";
    private String termsOfServiceUrl = "Insert Terms of Service here";
    private String license = "Insert License Type Here";
    private String licenseUrl = "URL To License";

    public SwaggerProperties() {
    }

    public String getApiDescription() {
        return this.apiDescription;
    }

    public String getApiLongName() {
        return this.apiLongName;
    }

    public String getApiVersion() {
        return this.apiVersion;
    }

    public String getDeveloperEmail() {
        return this.developerEmail;
    }

    public String[] getDocumentedEndpoints() {
        return this.documentedEndpoints;
    }

    public String getLicense() {
        return this.license;
    }

    public String getLicenseUrl() {
        return this.licenseUrl;
    }

    public String getTermsOfServiceUrl() {
        return this.termsOfServiceUrl;
    }

    public void setApiDescription(String apiDescription) {
        this.apiDescription = apiDescription;
    }

    public void setApiLongName(String apiLongName) {
        this.apiLongName = apiLongName;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public void setDeveloperEmail(String developerEmail) {
        this.developerEmail = developerEmail;
    }

    public void setDocumentedEndpoints(String[] documentedEndpoints) {
        this.documentedEndpoints = documentedEndpoints;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public void setLicenseUrl(String licenseUrl) {
        this.licenseUrl = licenseUrl;
    }

    public void setTermsOfServiceUrl(String termsOfServiceUrl) {
        this.termsOfServiceUrl = termsOfServiceUrl;
    }
}

