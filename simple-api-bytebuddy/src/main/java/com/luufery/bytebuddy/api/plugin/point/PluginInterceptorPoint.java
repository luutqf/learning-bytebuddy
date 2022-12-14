package com.luufery.bytebuddy.api.plugin.point;


import com.luufery.bytebuddy.api.advice.InterceptorPoint;
import com.luufery.bytebuddy.api.advice.RaspAdvice;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class PluginInterceptorPoint implements InterceptorPoint {


    private final String[] targetClass;

    private final List<RaspTransformationPoint<? extends RaspAdvice>> transformationPoint;

    public static PluginInterceptorPoint createDefault() {
        return new PluginInterceptorPoint(new String[0], Collections.<RaspTransformationPoint<? extends RaspAdvice>>emptyList());
    }


    public static Builder intercept(final String[] classNameOfTarget) {
        return new Builder(classNameOfTarget);
    }

    public static final class Builder {

        private final List<RaspTransformationPoint<? extends RaspAdvice>> transformationPoints = new ArrayList<RaspTransformationPoint<? extends RaspAdvice>>();

        private final String[] targetClass;

        private Builder(final String[] classNameOfTarget) {
            this.targetClass = classNameOfTarget;
        }


        public TransformationPointBuilder on(final ElementMatcher<? super MethodDescription> matcher) {
            return new TransformationPointBuilder(this, matcher);
        }

        public PluginInterceptorPoint install() {
            return new PluginInterceptorPoint(targetClass, transformationPoints);
        }

        public static final class TransformationPointBuilder {

            private final Builder builder;

            private final ElementMatcher<? super MethodDescription> matcher;

            private Class<? extends RaspAdvice> classOfAdvice;

            private TransformationPointBuilder(final Builder builder, final ElementMatcher<? super MethodDescription> matcher) {
                this.builder = builder;
                this.matcher = matcher;
            }

            public TransformationPointBuilder implement(final Class<? extends RaspAdvice> classOfAdvice) {
                this.classOfAdvice = classOfAdvice;
                return this;
            }

            public Builder build() {
                builder.transformationPoints.add(new RaspTransformationPoint<RaspAdvice>(matcher, classOfAdvice));
                return builder;
            }
        }
    }
}

