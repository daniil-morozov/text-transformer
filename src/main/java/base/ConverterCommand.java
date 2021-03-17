package base;

public interface ConverterCommand<From, To> {
    To convert(From from);
}
