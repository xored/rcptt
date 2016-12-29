# RCPTT Integration with TestRail

**Test Engine configuration parameters**:
* **testRailAddress** --- TestRail Address. Should be valid URL and end with slash "/".
* **testRailUsername** --- Username.
* **testRailPassword** --- Password or API Key.
* **testRailProjectId** --- Project ID. Should start with "P" and end with positive number.

**Examples**
For RCPTT Test Runner:

        -testEnginesConfig 'testRailAddress=https://example.testrail.net/;testRailUsername=username@example.com;testRailPassword=1234567890;testRailProjectId=P1'


For RCPTT Maven Plugin:

        <configuration>
          <testEnginesConfig>
            <testRailAddress>https://example.testrail.net/</testRailAddress>
            <testRailUsername>username@example.com</testRailUsername>
            <testRailPassword>1234567890</testRailPassword>
            <testRailProjectId>P1</testRailProjectId>
          </testEnginesConfig>
        </configuration>

