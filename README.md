# Kayla Soraya Djakaria - 2306256381

<details>
<summary>Module 1</summary>
### Reflection 1
I applied several clean code principles in my project, such as meaningful method names and 
separation of concerns to improve maintainability and readability.

For example, I used clear naming conventions like `findById` to describe methods accurately 
and structured the code by separating Controller, Service, and Repository layers since each has 
different responsibility.

Additionally, I followed secure coding practices, such as avoiding hardcoded identifiers by using
`UUID.randomUUID()` to prevent predictable IDs.

Throughout working on this project, I experienced a problem with editing the product. I missed 
putting hidden input for the product ID in the edit form page, which made the data not change. By 
identifying and resolving this issue, I reinforced the importance of proper data binding in form submissions.

### Reflection 2
After writing some unit tests, I think these help in maintaining our code correctness. I don't think there's an exact number 
of tests that should be created in a class, since if the test already cover the functionality and positive & negative cases,
then it should be enough. Another way to know if out tests are enough is by looking at the percentage of code coverage. However, 100% code
coverage doesn't guarantee my code is bug free because it might not check expected output correctly, and some edge cases might still 
be unaccounted for.

If a new functional test is created using same setup and variables, then this will create redundancy because of
repeated setup code. One way to improve this is by creating a parent class to store the setup. Also, instead of writing same code in multiple
places, we can use JUnit5's `@ParameterizedTest` to reuse test logic. This allows testing multiple products in one test case, reducing 
redundant test methods.
</details>

<details>
<summary>Module 2</summary>
- Unused imports can slow down the compilations: remove unused imports
- When public modifiers is not needed, it will expose implementation details: remove public modifiers when it's not needed
- Declaring code exception might reduce code clarity: remove exception declarations that are not needed

My current implementation meets the definition of Continuous Integration and Continuous Deployment. 
The `ci.yml` workflow automatically triggers builds and runs unit and integration tests on each commit, ensuring that all changes are verified before merging. 
The `deploy.yml` workflow automates the deployment process to Koyeb, allowing tested code to be pushed to production without manual intervention. 
Additionally, `sonarcloud.yml` integrates static code analysis using SonarCloud to detect bugs, vulnerabilities, and code smells to improve code quality and security.
</details>