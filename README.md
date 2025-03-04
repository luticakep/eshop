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

<details>
<summary>Module 3</summary>
1. I implement SRP, OCP, and LSP in my project.

- Single Responsibility Principles (SRP): I ensure all of my class or module has specific responsibility. I created a new file for CarController which was
previously inside ProductController. In addition, I separate the logic in service and data persistence in repository.  

- Open/Closed Principle (OCP): I created interfaces to be implemented by several classes (ICarRepository and IProductRepository). If I want to add a new type of repository, 
I can implement the same interface without modifying it.

- Liskov Substitution Principle (LSP): I already implement this principle because CarServiceImpl implements CarService and ProductServiceImpl implements ProductService, 
so different implementations can replace while the rest of the system still works correctly.


2. The advantages of applying SOLID principles 

- SRP: Each class has its own responsibility and this will make me easier to debug (easier to manage and read)

- OCP: I can add new features in my project without editing/altering an existing code. This will lower the chance of new bugs.
It will also make my project cleaner and more organized by extending classes through interfaces.

- LSP: This ensures class that implements interface can be swapped interchangeably and without breaking existing code. 

- ISP: Allows a more specific features by splitting to smaller ones. This will make it easier to maintain and understand.

- DIP: In my CarServiceImpl, it's depends on ICarRepository which is an interface and not a concrete implementations. This will help me in writing the unit tests.


3. Disadvantages of not applying SOLID principles

There are multiple disadvantages if SOLID principle are not implemented in my project. Without SRP, there will be classes that have multiple features 
, making it harder to understand. Without OCP, I will have to modify existing code if I want to add a new feature and this will increase the chance of bugs in my project. 
Without LSP, it will reduce code flexibility because derived classes might not follow the base classes. Without ISP, there'll be interfaces that are forced to implement unnecessary methods.
Without DIP, it will increase challenges in creating unit tests because of high level module depend on low level module. All of those will increase the chances of bugs and 
make it hard to debug and maintain.

</details>

<details>
<summary>Module 4</summary>

## Tutorial Reflection
TDD flow is useful as it helps me in creating cleaner code by maintaining separation of concern across my project. Furthermore, writing tests before implementing the functions can 
help me in identifying errors or bugs. Also, creating variation of possible conditions/scenarios are necessary in order to validate the functionalities (edge cases).

My tests have followed F.I.R.S.T principle. 
Fast: Use mocks to avoid any external dependencies
Independent: `@BeforeEach` in setUp initialize the product list with new product for each test
Repeatable: Produce same results for any scenarios
Self-validating: Clear assertion to identify fail/success 
Timely: Cover all possibility of results and errors

</details>