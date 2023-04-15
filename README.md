
### Dependency Diagram

```mermaid
%%{
  init: {
    'theme': 'neutral'
  }
}%%

graph LR

  data --> domain
  app --> domain
  app --> data

```