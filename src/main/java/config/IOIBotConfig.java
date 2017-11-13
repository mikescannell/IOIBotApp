package config;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IOIBotConfig {

    private String sessionAuthURL;

    private String keyAuthUrl;

    private String localKeystorePath;

    private String localKeystorePassword;

    private String botCertPath;

    private String botCertPassword;

    private String botEmailAddress;

    private String userEmailAddress;

    private String agentAPIEndpoint;

    private String podAPIEndpoint;


    @JsonProperty
    public String getSessionAuthURL() {
        return sessionAuthURL;
    }

    @JsonProperty("sessionAuthURL")
    public void setSessionAuthURL(String sessionAuthURL) {
        this.sessionAuthURL = sessionAuthURL;
    }

    @JsonProperty
    public String getKeyAuthUrl() {
        return keyAuthUrl;
    }

    @JsonProperty("keyAuthUrl")
    public void setKeyAuthUrl(String keyAuthUrl) {
        this.keyAuthUrl = keyAuthUrl;
    }

    @JsonProperty
    public String getLocalKeystorePath() {
        return localKeystorePath;
    }

    @JsonProperty("localKeystorePath")
    public void setLocalKeystorePath(String localKeystorePath) {
        this.localKeystorePath = localKeystorePath;
    }

    @JsonProperty
    public String getLocalKeystorePassword() {
        return localKeystorePassword;
    }

    @JsonProperty("localKeystorePassword")
    public void setLocalKeystorePassword(String localKeystorePassword) {
        this.localKeystorePassword = localKeystorePassword;
    }

    @JsonProperty
    public String getBotCertPath() {
        return botCertPath;
    }

    @JsonProperty("botCertPath")
    public void setBotCertPath(String botCertPath) {
        this.botCertPath = botCertPath;
    }

    @JsonProperty
    public String getBotCertPassword() {
        return botCertPassword;
    }

    @JsonProperty("botCertPassword")
    public void setBotCertPassword(String botCertPassword) {
        this.botCertPassword = botCertPassword;
    }

    @JsonProperty
    public String getBotEmailAddress() {
        return botEmailAddress;
    }

    @JsonProperty("botEmailAddress")
    public void setBotEmailAddress(String botEmailAddress) {
        this.botEmailAddress = botEmailAddress;
    }

    @JsonProperty
    public String getAgentAPIEndpoint() {
        return agentAPIEndpoint;
    }

    @JsonProperty("agentAPIEndpoint")
    public void setAgentAPIEndpoint(String agentAPIEndpoint) {
        this.agentAPIEndpoint = agentAPIEndpoint;
    }

    @JsonProperty
    public String getPodAPIEndpoint() {
        return podAPIEndpoint;
    }

    @JsonProperty("podAPIEndpoint")
    public void setPodAPIEndpoint(String podAPIEndpoint) {
        this.podAPIEndpoint = podAPIEndpoint;
    }

    @JsonProperty
    public String getUserEmailAddress() {
        return userEmailAddress;
    }

    @JsonProperty("userEmailAddress")
    public void setUserEmailAddress(String userEmailAddress) {
        this.userEmailAddress = userEmailAddress;
    }

}

