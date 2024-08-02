## 프로젝트 이름

### Java for RegexBuilder

---

## 프로젝트 개요

'RegexBuilder'는 정규식의 구성과 사용을 단순화하도록 설계된 Java 라이브러리입니다. 읽기 쉽고 유지 관리가 가능한 방식으로 복잡한 정규식 패턴을 구축하기 위한 API를 제공합니다.

---
## 프로젝트 특징
- 정규식 작성을 위한 Fluent API 를 제공합니다.
- 미리 정의된 문자 클래스를 지원합니다.
- 디버깅 및 로깅을 위한 정규식 패턴을 추출할 수 있습니다.
- 정규식 기반 문자열 대체 메서드를 제공합니다.
- 빌더를 통해 쉽게 생성이 가능합니다.

---
## 설치 방법

- Maven을 사용하는 경우, pom.xml에 다음을 추가하세요.

````
<dependency>
    <groupId>com.example</groupId>
    <artifactId>regexbuilder</artifactId>
    <version>1.0.0</version>
</dependency>
````

- Gradle을 사용하는 경우 build.gradle에 다음을 추가하세요.

````
implementation 'com.example:regexbuilder:1.0.0'
````

---

## 사용 방법

### 기본 사용 방법

````java
package org.example;

import org.example.utils.RegexBuilder;

public class Main {
    public static void main(String[] args) {
        RegexBuilder regex = new RegexBuilder()
            .start()
            .literal("Word")
            .whitespace()
            .digit().oneOrMore()
            .end();

        String input = "Word 1225";
        System.out.println("Pattern: " + regex.extract());
        System.out.println("Matches: " + regex.matches(input)); // Output: true
    }
}
````

### 자주 사용하는 정규식 (이메일)

````java
package org.example;

import org.example.utils.RegexBuilder;

public class Main {
    public static void main(String[] args) {
        RegexBuilder emailRegex = new RegexBuilder()
            .start()
            .word().oneOrMore()
            .literal("@")
            .word().oneOrMore()
            .literal(".")
            .word().oneOrMore()
            .end();

        String email = "hong777@example.com";
        System.out.println("Email Pattern: " + emailRegex.extract());
        System.out.println("Email Matches: " + emailRegex.matches(email)); // Output: true
    }
}

````
### 컴파일 옵션

````java
package org.example;

import org.example.utils.RegexBuilder;

public class Main {
    public static void main(String[] args) {
        RegexBuilder regex = new RegexBuilder()
            .start()
            .literal("user")
            .caseInsensitive()
            .whitespace()
            .digit().oneOrMore()
            .end();

        String input = "USER 0001";
        System.out.println("Pattern: " + regex.extract());
        System.out.println("Matches: " + regex.matches(input)); // Output: true
    }
}

````

### Substitution 예제

````java
package org.example;

import org.example.utils.RegexBuilder;

public class Main {
    public static void main(String[] args) {
        RegexBuilder regex = new RegexBuilder()
                .literal("<")
                .anyCharacterExcept(">")
                .oneOrMore()
                .literal(">");

        String input = "<tag>('Hello World')</tag>";
        String substituted = substitutePatten.replaceAll(input, "");
        System.out.println(substituted); // Output: ('Hello World')
    }
}
````

---
## API 문서

### Methods

- literal(String text): 패턴에 리터럴 문자열을 추가합니다.
- digit(): 패턴에 숫자(\d)를 추가합니다.
- nonDigit(): 패턴에 숫자가 아닌 것(\D)을 추가합니다.
- word(): 패턴에 단어 문자(\w)를 추가합니다.
- nonWord(): 패턴에 단어가 아닌 문자(\W)를 추가합니다.
- whitespace(): 패턴에 공백 문자(\s)를 추가합니다.
- nonWhitespace(): 패턴에 공백이 아닌 문자(\S)를 추가합니다.
- anyOf(String chars): 지정된 문자와 일치하는 문자열을 추가합니다.
- noneOf(String chars): 지정된 문자와 일치하지 않는 문자열을 추가합니다.
- oneOrMore(): 이전 토큰 중 하나 이상과 일치하는 문자(or 숫자)를 추가합니다.
- zeroOrMore(): 0개 이상의 이전 토큰과 일치하는 문자(or 숫자)를 추가합니다.
- optional(): 이전 토큰 중 0개 또는 1개와 일치하는 문자(or 숫자)를 추가합니다.
- exactly(int times): 지정된 횟수와 정확히 일치하는 문자(or 숫자)를 추가합니다.
- atLeast(int times): 지정된 횟수 이상과 일치하는 문자(or 숫자)를 추가합니다.
- between(int min, int max): 지정된 최소 횟수와 최대 횟수 사이에 일치하는 문자(or 숫자)를 추가합니다.
- start(): builder 시작 부분의 위치를 지정합니다.
- end(): builder 끝의 위치를 확인합니다.
- wordBoundary(): 단어 경계를 확인합니다.
- nonWordBoundary(): 단어가 아닌 경계를 확인합니다.
- caseInsensitive(): 대소문자를 구분하지 않고 일치하는지를 확인합니다.
- build(): 정규식 패턴을 문자열로 작성합니다.
- compile(): 정규식 패턴을 패턴 객체로 컴파일합니다.
- matches(String input): 입력 문자열을 컴파일된 패턴과 일치시킵니다.
- extract(): 현재 정규식 패턴을 문자열로 반환합니다.
- reset(): 빌더를 초기화 합니다.
- replaceAll(String input, String replacement): 입력 문자열의 모든 일치 항목을 지정된 대체 항목으로 바꿉니다.


---
## License

이 프로젝트는 MIT 라이선스에 따라 라이선스가 부여됩니다. 
자세한 내용은 LICENSE 파일을 참조해주세요.